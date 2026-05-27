package com.example.day3_5;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Page2 extends HttpServlet
{
    public void doPost(HttpServletRequest req,
                       HttpServletResponse res)
            throws IOException, ServletException
    {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();

        pw.println("<html><body>");

        String arr_vals[] = req.getParameterValues("items");
        int page1_ttl = 0;

        if (arr_vals != null)
        {
            for (int i = 0; i < arr_vals.length; i++)
                page1_ttl += Integer.parseInt(arr_vals[i]);
        }

        pw.println("<h2>Page1 total is Rs. " + page1_ttl + "</h2>");

        pw.println("<form action='P4' method='post'>");
        pw.println("<input type='checkbox' name='items' value='150'>Colour Box @Rs. 150/-<p>");
        pw.println("<input type='checkbox' name='items' value='100'>Compass Box @Rs. 100/-<p>");
        pw.println("<input type='submit' value='Shop>>'>");
        pw.println("</form>");

        HttpSession s = req.getSession(true);
        s.setAttribute("page1_ttl", new Integer(page1_ttl));

        pw.println("</body></html>");
    }
}