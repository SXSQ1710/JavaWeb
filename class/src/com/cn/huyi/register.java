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

@WebServlet(name="register" ,urlPatterns = {"/register-servelet"})
public class register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println(req.getParameter("username"));
        int username = Integer.parseInt(req.getParameter("username"));
        int password =  Integer.parseInt(req.getParameter("password"));
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
            if(new LoginToDaoImpl().adminRegister(username,password)){
                resp.setContentType("text/html;charset=utf-8");
                PrintWriter out = resp.getWriter();
                out.write("<script>alert('注册成功!')</script>");
                System.out.println("注册成功！");
            }else {
                resp.setContentType("text/html;charset=utf-8");
                PrintWriter out = resp.getWriter();
                out.write("<script>alert('注册失败!')</script>");
                System.out.println("注册失败！");
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}