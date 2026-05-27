package com.example.day3_4;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/DeleteDestinationServlet")
public class DeleteDestinationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || !"admin".equals(session.getAttribute("username"))) {
            response.sendRedirect("login.jsp");
            return;
        }

        String place = request.getParameter("place");

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM destinations WHERE place=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, place);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        response.sendRedirect("admin.jsp");
    }
}
