package cn.com.huyi.controller;

import cn.com.huyi.business.factory.EBIFactory;
import cn.com.huyi.common.ViewBaseServlet;
import cn.com.huyi.entity.BookModel;
import cn.com.huyi.entity.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

/**
 * @title: BookServlet
 * @Author SXSQ
 * @Description //TODO
 * @Date 2022/4/3 21:06
 **/

@WebServlet("/BookSystem")
public class BookServlet extends ViewBaseServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //post方式下，设置编码，防止返回中文乱码
//        request.setCharacterEncoding("UTF-8");
        String operate = request.getParameter("operate");
        Method[] methods = getClass().getDeclaredMethods();
        for (Method method : methods){
            String name = method.getName();
            if (name.equals(operate)){
                try {
                    System.out.println("调用方法"+name);
                    method.invoke(this,request,response);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @Description //TODO 处理登录请求
     * @param req
     * @param resp
     */
    private void Login(HttpServletRequest req, HttpServletResponse resp){
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User(username, password);
        try {
            if(EBIFactory.getUserEBI().adminLogin(user)){
                resp.setContentType("text/html;charset=utf-8");
                PrintWriter out = resp.getWriter();
                out.write("<script>alert('登录成功!')</script>");
                System.out.println("登录成功！");
                resp.sendRedirect("BookSystem?username="+username+"&operate=index");
            }else {
                resp.setContentType("text/html;charset=utf-8");
                PrintWriter out = resp.getWriter();
                out.write("<script>alert('登录失败!')</script>");
                System.out.println("登录失败！");
                super.processTemplate("login",req,resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description //TODO 处理注册请求
     * @param req
     * @param resp
     */
    private void Regist(HttpServletRequest req, HttpServletResponse resp){
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User(username, password);
        System.out.println(username);
        System.out.println(password);
        try {
            if(EBIFactory.getUserEBI().adminRegist(user)){
                resp.setContentType("text/html;charset=utf-8");
                PrintWriter out = resp.getWriter();
                out.write("<script>alert('注册成功!')</script>");
                System.out.println("注册成功！");
                super.processTemplate("login",req,resp);
            }else {
                resp.setContentType("text/html;charset=utf-8");
                PrintWriter out = resp.getWriter();
                out.write("<script>alert('注册失败!')</script>");
                System.out.println("注册失败！");
                super.processTemplate("Register",req,resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description //TODO 加载添加页面
     * @param request
     * @param response
     * @throws IOException
     */
    protected void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArrayList<String> allSort = BookModel.getAllSort();
        request.setAttribute("AllSort",allSort);
        super.processTemplate("add",request,response);
    }

    /**
     * @Description //TODO 加载修改页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void alet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        String isbn = request.getParameter("isbn");
        BookModel aBook = EBIFactory.getBookEBI().getABookByIsbn(isbn);
        request.setAttribute("aBook",aBook);
        super.processTemplate("alet",request,response);
    }

    /**
     * @Description //TODO 加载主页面，并刷新
     * @param request
     * @param response
     * @throws IOException
     */
    private void index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArrayList<BookModel> allBook = EBIFactory.getBookEBI().getAllBook();
        HttpSession session = request.getSession();
        session.setAttribute("allBook",allBook);
        super.processTemplate("index",request,response);
    }


    /**
     * @Description //TODO 加载搜索结果页面
     * @param request
     * @param response
     * @throws IOException
     */
    private void search(HttpServletRequest request, HttpServletResponse response) throws IOException {
        super.processTemplate("search",request,response);
    }

    /**
     * @Description //TODO index页面发送处理请求，处理主页面中的搜索功能，搜索结果跳转到search页面
     * @param request
     * @param response
     * @throws IOException
     */
    private void GetABook(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String isbnorname = request.getParameter("isbnorname");
        ArrayList<BookModel> aBook = new ArrayList<>();
        if (isbnorname == null){
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write("<script>alert('请输入正确的信息!')</script>");
            System.out.println("请输入正确的信息!");

        }else if (isbnorname.equals("radioIsbn")){
            String isbn = request.getParameter("isbn");
            BookModel aBookByIsbn = EBIFactory.getBookEBI().getABookByIsbn(isbn);
            if (aBookByIsbn!=null) aBook.add(aBookByIsbn);
            HttpSession session = request.getSession();
            session.setAttribute("aBook",aBook);
            response.sendRedirect("BookSystem?&operate=search");
        }else if(isbnorname.equals("radioName")){
            String name = request.getParameter("name");
            BookModel aBookByName = EBIFactory.getBookEBI().getABookByName(name);
            if (aBookByName!=null) aBook.add(aBookByName);
            HttpSession session = request.getSession();
            session.setAttribute("aBook",aBook);
            response.sendRedirect("BookSystem?&operate=search");
        }else {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write("<script>alert('请输入正确的信息!')</script>");
            System.out.println("请输入正确的信息!");
        }
    }

    /**
     * @Description //TODO BookSystem.js发送处理请求，处理删除功能,处理完成调整index页面
     * @param request
     * @param response
     * @throws IOException
     */
    private void deletBook(HttpServletRequest request, HttpServletResponse response) throws  IOException{
        String isbn = request.getParameter("isbn");
        boolean b = EBIFactory.getBookEBI().deleteBook(isbn);
        if(b){
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write("<script>alert('删除成功!')</script>");
            System.out.println("删除成功！");
            response.sendRedirect("BookSystem?&operate=index");
        }else {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write("<script>alert('删除失败!')</script>");
            System.out.println("删除失败！");
        }
    }

    /**
     * @Description //TODO add页面发送处理请求，处理添加功能，处理完成跳转index页面
     * @param request
     * @param response
     * @throws IOException
     */
    private void addBook(HttpServletRequest request, HttpServletResponse response) throws  IOException{
        String ibsn = request.getParameter("ibsn");
        String name = request.getParameter("name");
        String sort = request.getParameter("sort");
        int price = Integer.parseInt(request.getParameter("price"));
        BookModel book = new BookModel(ibsn, name, sort, price);
        System.out.println(book);
        boolean b = EBIFactory.getBookEBI().addBook(book);
        if(b){
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write("<script>alert('添加成功!')</script>");
            System.out.println("添加成功！");
            response.sendRedirect("BookSystem?&operate=index");
        }else {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write("<script>alert('添加失败!')</script>");
            System.out.println("添加失败！");
        }
    }

    /**
     * @Description //TODO alet页面发送处理请求，处理修改功能，跳转index页面
     * @param request
     * @param response
     * @throws IOException
     */
    private void aletBook(HttpServletRequest request, HttpServletResponse response) throws  IOException{
        String ibsn = request.getParameter("ibsn");
        String name = request.getParameter("name");
        String sort = request.getParameter("sort");
        int price = Double.valueOf(request.getParameter("price")).intValue();
        BookModel book = new BookModel(ibsn, name, sort, price);
        System.out.println(book);
        boolean b = EBIFactory.getBookEBI().updateBook(book,ibsn);
        if(b){
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write("<script>alert('修改成功!')</script>");
            System.out.println("修改成功！");
            response.sendRedirect("BookSystem?&operate=index");
        }else {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write("<script>alert('修改失败!')</script>");
            System.out.println("修改失败！");
        }
    }


}
