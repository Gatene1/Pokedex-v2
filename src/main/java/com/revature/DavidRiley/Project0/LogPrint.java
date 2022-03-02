package com.revature.DavidRiley.Project0;

import jakarta.servlet.Servlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import static org.h2.util.Utils.getResource;

public class LogPrint extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Log logClass = new Log();
        logClass.AddToLog("Viewed Log",1);
        try {
            URL logFile = new URL("file:///D:/rev/Project0/UserLog.dat");
            BufferedReader br = new BufferedReader(new InputStreamReader(logFile.openStream()));

            resp.getWriter().println("<html>" +
                    "                   <head>" +
                    "                       <title>Log of User Pokedex Experience</title>" +
                    "                       <style>" +
                    "                           a {color:#000000;}" +
                    "                           a:hover {font-weight:bold;}" +
                    "                           .highlight:hover {background-color:#d7d7ff;}" +
                    "                       </style>" +
                    "                   </head>" +
                    "                   <body>" +
                    "                       <table name='logTable' border=2 cellspacing=0 cellpdadding=0>");

            String lineRead;
            while ((lineRead = br.readLine()) != null) {
                resp.getWriter().println("<tr class='highlight'>" +
                        "               <td>" +
                        "                   " + lineRead +
                        "                       </td>" +
                        "             </tr>");
            }
            resp.getWriter().println("</table>" +
                            "           </body>" +
                            "        </html>");
            br.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
