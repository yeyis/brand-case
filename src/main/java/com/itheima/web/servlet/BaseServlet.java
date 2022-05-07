package com.itheima.web.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 替换HttpServlet,根据请求的最后一段路径来进行方法分发
 */
public class BaseServlet extends HttpServlet {

    //根据请求的最后一段来进行方法分发

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求路径
        String uri = req.getRequestURI();
        //2.获取最后一段路径名
        int index = uri.lastIndexOf('/');
        String methodName = uri.substring(index + 1);
        //3.获取到class对象
        Class c = this.getClass();
        //4.获取到method
        try {
            Method method = c.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
