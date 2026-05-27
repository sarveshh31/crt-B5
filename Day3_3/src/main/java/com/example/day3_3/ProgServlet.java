package com.example.day3_3;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.*;

@WebServlet("/tour")
public class ProgServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String[] places = request.getParameterValues("place");

        out.println("<html><body>");

        // Validation
        if (places == null) {
            out.println("<h2>Please select at least 1 destination</h2>");
        } else {

            int total = 0;

            out.println("<h2>Selected Destinations:</h2>");

            for (String p : places) {

                if (p.equals("agra")) {
                    out.println("<h3>Agra</h3>");
                    out.println("Stay: <a href='https://www.tajhotels.com' target='_blank'>Hotel Taj</a><br>");
                    out.println("Places: Taj Mahal, Agra Fort<br>");
                    out.println("Cost: 2000<br><br>");
                    total += 2000;
                }

                if (p.equals("amritsar")) {
                    out.println("<h3>Amritsar</h3>");
                    out.println("Stay: <a href='https://www.ihcltata.com' target='_blank'>Hotel Punjab</a><br>");
                    out.println("Places: Golden Temple, Wagah Border<br>");
                    out.println("Cost: 3000<br><br>");
                    total += 3000;
                }

                if (p.equals("delhi")) {
                    out.println("<h3>Delhi</h3>");
                    out.println("Stay: <a href='https://www.oberoihotels.com' target='_blank'>Hotel Delhi</a><br>");
                    out.println("Places: Red Fort, India Gate<br>");
                    out.println("Cost: 2500<br><br>");
                    total += 2500;
                }
            }

            out.println("<h2>Total Cost: " + total + "</h2>");
        }

        out.println("</body></html>");
    }
}