package com.linus.lib.springmvc.framework.servlet;

import com.linus.lib.springmvc.framework.annotation.LabController;
import com.linus.lib.springmvc.framework.annotation.LabRequestMapping;
import com.linus.lib.springmvc.framework.context.LabWebApplicationContext;
import com.linus.lib.springmvc.framework.handler.LabServletHandler;
import jdk.nashorn.internal.runtime.options.Option;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/10/18
 */
public class LabDispatchServlet extends HttpServlet {

    private LabWebApplicationContext context;

    private List<LabServletHandler> labServletHandlerList = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        //1 加载初始化参数
        String contextConfigLocation = this.getServletConfig().getInitParameter("contextConfigLocation");

        //2 创建springmvc容器
        this.context = new LabWebApplicationContext(contextConfigLocation);

        //3 初始化容器
        context.onRefresh();

        //4 初始请求映射关系
        initHandlerMapping();
    }
    /**
     * initHandlerMapping
     */
    public void initHandlerMapping() {

        try {
            for (Map.Entry<String, Object> entry : context.singletonObjects.entrySet()) {
                Object object = entry.getValue();
                Class<?> clazz = object.getClass();
                if (clazz.isAnnotationPresent(LabController.class)) {
                    for (Method method : clazz.getMethods()) {
                        if (method.isAnnotationPresent(LabRequestMapping.class)) {
                            LabRequestMapping labRequestMapping = method.getAnnotation(LabRequestMapping.class);
                            String url = labRequestMapping.value();
                            LabServletHandler handler = new LabServletHandler(url, object, method);
                            labServletHandlerList.add(handler);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDispatch(req, resp);
    }

    public void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LabServletHandler handler = getHandler(req);
        if (handler == null) {
            resp.getWriter().print("<h1>404</h1>");
            return;
        }
        //TODO 参数注入
        try {
            Object result = handler.getMethod().invoke(handler.getHandler());
            //
            String page = (String) result;
            req.getRequestDispatcher(page).forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private LabServletHandler getHandler(HttpServletRequest req) {
        Optional<LabServletHandler> option = labServletHandlerList.stream().filter(handler->handler.getUrl().equals(req.getRequestURI())).findAny();
        return option.get();
    }

}
