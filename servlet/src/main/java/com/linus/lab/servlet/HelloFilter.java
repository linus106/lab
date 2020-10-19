package com.linus.lab.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/10/19
 */
public class HelloFilter implements Filter {

    public HelloFilter() {
        System.out.println("instant HelloFilter");
    }

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("HelloFilter called!");
        chain.doFilter(request, response);
    }

    public void destroy() {

    }
}
