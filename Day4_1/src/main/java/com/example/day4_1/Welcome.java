package com.example.day4_1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/WEL")
public class Welcome extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter pw = response.getWriter();

        try {
            HttpSession s = request.getSession(false);
            String username = (String) s.getAttribute("username");
            s.setMaxInactiveInterval(30);

            pw.println("<html>");
            pw.println("<head>");
            pw.println("<meta http-equiv='refresh' content='30;url=invalid.jsp'>");
            pw.println("</head>");
            pw.println("<body>");
            pw.println("<h3>Welcome user " + username + " to our Website!</h3>");
            pw.println("<h3>Facilities Available</h3>");
            pw.println("1. Fund Transfer<p>");
            pw.println("2. Pay Bills Online<p>");
            pw.println("3. <a href='BB'>Check My Balance</a><p>");
            pw.println("</body></html>");

        } catch (NullPointerException e) {
            response.sendRedirect("invalid.jsp");
        }
    }
}