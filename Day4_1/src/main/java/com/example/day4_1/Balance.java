package com.example.day4_1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/BB")
public class Balance extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter pw = response.getWriter();
        pw.println("<html><body>");

        try {
            HttpSession s = request.getSession(false);
            String name = s.getAttribute("username").toString();
            pw.println("<h3>Welcome user " + name + " to our Website!</h3>");
            s.setMaxInactiveInterval(30);

            Object bal = s.getAttribute("Bal");
            pw.println("<b>Your Bank Balance is Rs. " + bal + "</b><p>");

            pw.println("<form action='LO' method='post'>");
            pw.println("<input type='submit' value='Logout'>");
            pw.println("</form></body></html>");

        } catch (NullPointerException e) {
            response.sendRedirect("invalid.jsp");
        }

        pw.close();
    }
}