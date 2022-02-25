package com.revature.DavidRiley.Project0;

import jakarta.servlet.Servlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;

/** DexSearch handles the searches in the CSV file.
 *
 */

public class DexSearch extends HttpServlet {
    public DexSearch() {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String fileName = "pokedex.csv";


    }
}
