package com.revature.DavidRiley.Project0;

import jakarta.servlet.Servlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.Map;

public class EnvVar extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, String> env = System.getenv();
        try {


            resp.getWriter().println("<html>" +
                            "           <head>" +
                            "               <title>Environment Variables for User Computer</title>" +
                            "           </head>" +
                            "           <style>" +
                            "               .highlight:hover {background-color:#d7d7ff;}" +
                            "           </style>" +
                            "           <body>" +
                            "               <table name='envVars' border=2 cellspacing=0 cellpadding=0>");

        for (String envName : env.keySet()) {
            resp.getWriter().println("<tr class='highlight'>" +
                            "           <td>"+envName+"</td>" +
                            "           <td>"+env.get(envName)+"</td>" +
                            "         </tr>");
        }
        resp.getWriter().println("</table>" +
                "           </body>" +
                "        </html>");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
