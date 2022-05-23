package cn.com.huyi.controller;

import cn.com.huyi.business.factory.EBIFactory;
import cn.com.huyi.entity.User;

import javax.print.DocFlavor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @title: UserController
 * @Author SXSQ
 * @Description //TODO
 * @Date 2022/4/12 16:40
 **/

public class UserController {
        /**
     * @Description //TODO 处理登录请求
     * @param req
     * @param resp
     */
    private String Login(HttpServletRequest req, HttpServletResponse resp){
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User(username, password);
        try {
            if(EBIFactory.getUserEBI().adminLogin(user)){
                resp.setContentType("text/html;charset=utf-8");
                PrintWriter out = resp.getWriter();
                out.write("<script>alert('登录成功!')</script>");
                System.out.println("登录成功！");
//                resp.sendRedirect("BookSystem?&operate=index");
                return "redirect:BookSystem.do";
            }else {
                resp.setContentType("text/html;charset=utf-8");
                PrintWriter out = resp.getWriter();
                out.write("<script>alert('登录失败!')</script>");
                System.out.println("登录失败！");
//                super.processTemplate("login",req,resp);
                resp.sendRedirect("LoginJsp.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description //TODO 处理注册请求
     * @param req
     * @param resp
     */
    private String Regist(HttpServletRequest req, HttpServletResponse resp){
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
//                super.processTemplate("login",req,resp);
                resp.sendRedirect("LoginJsp.jsp");
            }else {
                resp.setContentType("text/html;charset=utf-8");
                PrintWriter out = resp.getWriter();
                out.write("<script>alert('注册失败!')</script>");
                System.out.println("注册失败！");
//                super.processTemplate("Register",req,resp);
                resp.sendRedirect("RegisterJsp.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }
}
