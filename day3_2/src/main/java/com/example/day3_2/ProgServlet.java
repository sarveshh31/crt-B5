package com.example.day3_2;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/color")
public class ProgServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        String color = request.getParameter("color");

        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h1 style='color:" + color + "'>");
        out.println("This is your fav " + color + " color");
        out.println("</h1>");
        out.println("</body></html>");

        out.close();
    }
}