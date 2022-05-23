package cn.com.huyi.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @title: TestToHtmlFilter
 * @Author SXSQ
 * @Description //TODO
 * @Date 2022/5/19 11:15
 **/

public class TestToHtmlFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest hreq = (HttpServletRequest) servletRequest;
        HttpServletResponse hresp = (HttpServletResponse) servletResponse;
        NonCachingRequestWrapper ncrw = new NonCachingRequestWrapper(hreq);
        filterChain.doFilter(ncrw,hresp);
    }
}
