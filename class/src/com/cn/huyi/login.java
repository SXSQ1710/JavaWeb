package com.cn.huyi;
import com.cn.huyi.Dao.DaoException;
import com.cn.huyi.Dao.LoginToDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="login" ,urlPatterns = {"/login-servelet"},loadOnStartup = 1)
public class login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //podt方式下，设置编码，防止返回中文乱码
        req.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        //连接数据库
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动加载失败");
            e.printStackTrace();
        }

        try {
            if(new LoginToDaoImpl().adminIsReal(Integer.parseInt(username),Integer.parseInt(password))){
                resp.setContentType("text/html;charset=utf-8");
                PrintWriter out = resp.getWriter();
                out.write("<script>alert('登录成功!')</script>");
                System.out.println("登录成功！");
            }else {
                resp.setContentType("text/html;charset=utf-8");
                PrintWriter out = resp.getWriter();
                out.write("<script>alert('登录失败!')</script>");
                System.out.println("登录失败！");
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
