package cn.com.huyi;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//演示服务器内部转发
public class sessionTest04 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("sessionTest04...");
        //服务器内部转发
        req.getRequestDispatcher("sessionTest05").forward(req,resp);
    }
}
