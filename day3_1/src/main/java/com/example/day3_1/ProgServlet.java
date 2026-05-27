package com.example.day3_1;

import java.io.*;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

public class ProgServlet extends GenericServlet {

    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
       res.setContentType("text/html");
       PrintWriter pw = res.getWriter();
       pw.println("<html><body><h2>");
       String wish = "Good";
       Date d = new Date();
       int hrs = d.getHours();
       if (hrs < 12) {
           wish += "Morning";
       }
       else if (hrs < 17) {
           wish += "Afternoon";
       }
       else {
           wish += "Evening";
       }
       pw.println(wish + "</body></html>");
       pw.close();
    }

}