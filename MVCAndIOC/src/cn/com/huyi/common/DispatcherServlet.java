package cn.com.huyi.common;

import cn.com.huyi.common.ViewBaseServlet;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @title: DispatcherServlet
 * @Author SXSQ
// * @Description //TODO 中央控制器/核心控制器
 * @Date 2022/4/10 20:36
 **/

public class DispatcherServlet extends ViewBaseServlet {
    Map<String,Object>beanMap = new HashMap<>();
    private String contextConfigLocation;

    public DispatcherServlet() {
    }

    public void init() throws ServletException {
        super.init();
        try {
            contextConfigLocation = getInitParameter("contextConfig");
            /**
             * Java Document生成和解析XML
             * TODO 详细步骤请参考 https://blog.csdn.net/p812438109/article/details/81807440
             */
            InputStream applicationContezt = getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
            //1.创建DocumentBuilderFactory
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            //2.创建DocumentBuilder对象
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            //3.创建Document对象
            Document document = documentBuilder.parse(applicationContezt);
            //4.获取所有的bean节点
            NodeList beanNodeList = document.getElementsByTagName("bean");
            for (int i = 0; i < beanNodeList.getLength(); i++) {
                Node beanNode = beanNodeList.item(i);
                System.out.println(beanNode.getNodeType());
                if (beanNode.getNodeType()==Node.ELEMENT_NODE){
                    Element beanElement = (Element) beanNode;
                    String id = beanElement.getAttribute("id");
                    String aClass = beanElement.getAttribute("class");
                    //创建bean实例
                    Object beanobj = Class.forName(aClass).getDeclaredConstructor().newInstance();
                    //将bean实例对象保存到map容器中
                    beanMap.put(id , beanobj);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException | ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //设置编码
//        request.setCharacterEncoding("UTF-8");
        //剪切url  如/login.do  -->  login
        String servletPath = request.getServletPath();
        System.out.println("servletPath:" + servletPath);
        int lastIndexOf = servletPath.lastIndexOf(".do");
        servletPath = servletPath.substring(1,lastIndexOf);
        Object controllerBeanObj = beanMap.get(servletPath);
        String operate = request.getParameter("operate");
        if (StringUtil.isEmpty(operate)) operate = "index";
        else if ("error".equals(operate)) throw new RuntimeException("operate值error错误！");
        try {
            Method method = controllerBeanObj.getClass().getDeclaredMethod(operate, HttpServletRequest.class, HttpServletResponse.class);
            if (method!=null){

                // 2.调用controller组件中的operate对应的方法
                method.setAccessible(true);
                System.out.println("调用方法"+method.getName());
                Object returnObj = method.invoke(controllerBeanObj, request, response);

                // 3.视图处理
                String methodRetureStr = (String) returnObj;
                if (methodRetureStr.startsWith("redirect:")){
                    String redirectStr = methodRetureStr.substring("redirect:".length());
                    response.sendRedirect(redirectStr);
                }else {
                    super.processTemplate(methodRetureStr,request,response);
                }
            } else {
                throw new RuntimeException("operate值非法！");
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }


    }
}
