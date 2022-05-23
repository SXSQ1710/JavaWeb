package huyi;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 * @title: PerformanceListener
 * @Author SXSQ
 * @Description //TODO
 * @Date 2022/5/18 11:12
 **/

@WebListener
public class PerformanceListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        Long eTime = System.nanoTime();
        servletRequestEvent.getServletRequest().setAttribute("eTime",eTime);
        Long sTime = (Long)servletRequestEvent.getServletRequest().getAttribute("sTime");
        String uri = ((HttpServletRequest)servletRequestEvent.getServletRequest()).getRequestURI();
        System.out.println("访问资源"+uri+"耗费时间:"+(eTime - sTime)+"ns");
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        Long sTime = System.nanoTime();
        servletRequestEvent.getServletRequest().setAttribute("sTime",sTime);
    }
}
