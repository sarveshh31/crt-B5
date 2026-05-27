package com.example.day3_5;

import java.io.*;
import javax.servlet.http.*;

public class Page3 extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html><body>");

        String arr_vals[] = req.getParameterValues("items");
        int page2_ttl = 0;
        if (arr_vals != null) {
            for (int i = 0; i < arr_vals.length; i++) {
                page2_ttl += Integer.parseInt(arr_vals[i]);
            }
        }

        try {
            HttpSession session = req.getSession(false);
            int page1_ttl = Integer.parseInt(session.getAttribute("page1_ttl").toString());
            int grand_ttl = page1_ttl + page2_ttl;
            out.println("<h2>Page1 Total is Rs. " + page1_ttl + "</h2>");
            out.println("<h2>Page2 Total is Rs. " + page2_ttl + "</h2>");
            out.println("<h2>Grand Total is Rs. " + grand_ttl + "</h2>");
            out.println("<h2>Session ID: " + session.getId() + "</h2>");
        } catch (NullPointerException e) {
            out.println("<h2>No valid session for user</h2>");
        }
        out.println("</body></html>");
        out.close();
    }
}