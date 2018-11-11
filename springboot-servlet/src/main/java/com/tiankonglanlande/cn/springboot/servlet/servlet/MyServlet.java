package com.tiankonglanlande.cn.springboot.servlet.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/my/servlet",asyncSupported = true)
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

        AsyncContext asyncContext = req.getAsyncContext();
        asyncContext.start(()->{
            try {
                resp.getWriter().print("hello servlet!");
                asyncContext.complete();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });


    }
}
