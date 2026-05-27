package com.example.day4_1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Login")
public class Login extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        pw.println("<html><body>");

        String un = "", pwd = "";

        try {
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if (c.getName().equals("username")) un  = c.getValue();
                    if (c.getName().equals("password")) pwd = c.getValue();
                }
            }
        } catch (Exception e) {
            // no cookies, fields stay blank
        }

        pw.println("<h2>Login</h2>");
        pw.println("<form action='Process' method='post'>");
        pw.println("Username: <input type='text' name='uname' value='" + un + "'><p>");
        pw.println("Password: <input type='password' name='pwd' value='" + pwd + "'><p>");
        pw.println("<input type='checkbox' name='rem'> Remember Me <p>");
        pw.println("<input type='submit' value='Login'>");
        pw.println("</form>");
        pw.println("</body></html>");
        pw.close();
    }
}