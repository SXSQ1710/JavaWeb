package cn.com.huyi.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @title: NonCachingRequestWrapper
 * @Author SXSQ
 * @Description //TODO
 * @Date 2022/5/19 11:11
 **/

public class NonCachingRequestWrapper extends HttpServletRequestWrapper {
    public NonCachingRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getHeader(String name) {
        System.out.println(name+":"+super.getHeader(name));
        if (name.equals("if-Modified-Since")) return null;
        else return super.getHeader(name);
    }
}
