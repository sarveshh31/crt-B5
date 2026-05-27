package com.example.day4_1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/Process")
public class Process extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        String uname = request.getParameter("uname");
        String pwd   = request.getParameter("pwd");
        String rem   = request.getParameter("rem");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bank?useSSL=false&allowPublicKeyRetrieval=true",
                    "root", "pass@123"
            );

            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, uname);
            ps.setString(2, pwd);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("username", uname);
                session.setAttribute("Bal", rs.getInt("balance"));

                // save cookie if remember me checked
                if (rem != null) {
                    Cookie c1 = new Cookie("username", uname);
                    Cookie c2 = new Cookie("password", pwd);
                    c1.setMaxAge(120); // 2 mins
                    c2.setMaxAge(120);
                    response.addCookie(c1);
                    response.addCookie(c2);
                }

                rs.close(); ps.close(); conn.close();
                response.sendRedirect("WEL");
            } else {
                rs.close(); ps.close(); conn.close();
                response.sendRedirect("invalid.jsp");
            }

        } catch (Exception e) {
            pw.println("<h3>Error: " + e.getMessage() + "</h3>");
            e.printStackTrace();
        }
    }
}