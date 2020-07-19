package com.ramchandra.samples.javawarhello;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(urlPatterns = "/hello", loadOnStartup = 1)
public class HelloServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("Welcome to DevOps world. Happy Learning :-)");
    }
}
