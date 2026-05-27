package com.example.day3_4;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/AddDestinationServlet")
public class AddDestinationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || !"admin".equals(session.getAttribute("username"))) {
            response.sendRedirect("login.jsp");
            return;
        }

        String place = request.getParameter("place");
        String city = request.getParameter("city");
        String cost = request.getParameter("cost");
        String placesToVisit = request.getParameter("places_to_visit");
        String hotel = request.getParameter("hotel");

        try (Connection conn = com.example.day3_4.DBConnection.getConnection()) {
            String sql = "INSERT INTO destinations (place, city, cost, places_to_visit, hotel) VALUES (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, place);
            ps.setString(2, city);
            ps.setDouble(3, Double.parseDouble(cost));
            ps.setString(4, placesToVisit);
            ps.setString(5, hotel);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        response.sendRedirect("admin.jsp");
    }
}
