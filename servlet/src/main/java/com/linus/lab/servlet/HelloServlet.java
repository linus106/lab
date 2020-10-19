package com.linus.lab.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/10/19
 */
public class HelloServlet extends HttpServlet {

    public HelloServlet() {
        System.out.println("instant HelloServlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("action").equals("hello")) {
            hello(req, resp);
        }
    }

    private void hello(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("HelloServlet called!");
        resp.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
