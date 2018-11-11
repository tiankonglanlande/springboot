package com.tiankonglanlande.cn.springboot.servlet.servlet;

import org.springframework.scheduling.annotation.Async;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 天空蓝蓝的
 */
@WebServlet(urlPatterns = "/my/ayncservlet",asyncSupported = true)
public class MyAyncServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AsyncContext asyncContext = req.startAsync();
        asyncContext.start(()->{
            try {
                resp.getWriter().print("hello this is MyAyncServlet!");
                asyncContext.complete();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });


    }
}
