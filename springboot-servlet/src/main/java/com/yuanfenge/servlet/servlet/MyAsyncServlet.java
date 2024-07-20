package com.yuanfenge.servlet.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 猿份哥
 */
@WebServlet(urlPatterns = "/my/ayncservlet", asyncSupported = true)
public class MyAsyncServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AsyncContext asyncContext = req.startAsync();
        asyncContext.start(() -> {
            try {
                resp.getWriter().print("hello this is MyAyncServlet!");
                //业务代码。。。。。
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                //关闭进程返回流信息
                asyncContext.complete();
            }

        });


    }
}
