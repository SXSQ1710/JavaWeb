package cn.com.huyi.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @title: CharsetEncodingFilter
 * @Author SXSQ
 * @Description //TODO
 * @Date 2022/5/19 10:18
 **/

public class CharsetEncodingFilter implements Filter {
    String defaultCharset = "UTF-8";
    String charset = "";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String xmlCharset = filterConfig.getInitParameter("charset");
        if (xmlCharset == null) this.charset = this.defaultCharset;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest hreq = (HttpServletRequest) servletRequest;
        HttpServletResponse hresp = (HttpServletResponse) servletResponse;
        System.out.println("\ndoCharsetEncodingFilter");
        hreq.setCharacterEncoding(this.charset);
        hresp.setCharacterEncoding(this.charset);
        filterChain.doFilter(hreq,hresp);
    }
}
