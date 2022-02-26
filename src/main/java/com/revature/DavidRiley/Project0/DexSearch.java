package com.revature.DavidRiley.Project0;

import jakarta.servlet.Servlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.Scanner;

/** DexSearch handles the searches in the CSV file.
 *
 */

public class DexSearch extends HttpServlet {
    public DexSearch() {

    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String pokeListed = req.getParameter("pokeName");
        String lineRead;

        InputStream fileName = getClass().getClassLoader().getResourceAsStream("pokedex.csv");
        Scanner scanner = new Scanner(fileName, "UTF-8");
        scanner.useDelimiter("\n");

        while (scanner.hasNext()) {
            try {
                lineRead = scanner.next();
                if (pokeListed == null || pokeListed.equals(" "))
                    resp.getWriter().println(lineRead);
                else {
                    if (lineRead.contains(pokeListed))
                        resp.getWriter().println(lineRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // This while loop is necessary, because if there is no Pokemon listed
        // in the path of the URL, then I want each line to print to the server.
        // On the flip side, if there is a Pokemon listed, I want it printed.


    }
}
