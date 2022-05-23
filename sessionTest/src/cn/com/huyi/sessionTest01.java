package cn.com.huyi;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//演示获取session
public class sessionTest01 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取session
        HttpSession session = req.getSession();
        //session的非激活间隔时长，默认半个小时
        int Time = session.getMaxInactiveInterval();
        System.out.println("sessionID is :"+session.getId());
        System.out.println("sessionMaxTime is :"+Time);
    }
}
