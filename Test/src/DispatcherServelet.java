import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @title: DispatcherServelet
 * @Author SXSQ
 * @Description //TODO
 * @Date 2022/4/5 19:34
 **/

@WebServlet("*.html")
public class DispatcherServelet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String servletPath = request.getServletPath();
        System.out.println("servletPath="+servletPath);
        String subservletPath1 = servletPath.substring(1);
        System.out.println("subservletPath1="+subservletPath1);
        int lastDotIndexOf = servletPath.lastIndexOf(".html");
        String subservletPath2 = subservletPath1.substring(0, lastDotIndexOf-1);
        System.out.println("subservletPath2="+subservletPath2);


    }
}
