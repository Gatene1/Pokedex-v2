package com.revature.DavidRiley.Project0;

import jakarta.servlet.Servlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

/** DexSearch handles the searches that search through the CSV file.
 *
 */

public class DexSearch extends HttpServlet {
    public DexSearch() {

    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String pokeListed = req.getParameter("searchPoke");
        String lineRead;
        String[] pokeStringList = new String[15];
        ArrayList<Pokemon> pokeList = new ArrayList<Pokemon>();
        Pokemon pokemon = new Pokemon();

        InputStream fileName = getClass().getClassLoader().getResourceAsStream("pokedex.csv");
        Scanner scanner = new Scanner(fileName, "UTF-8");
        scanner.useDelimiter("\n");
        try {
            resp.getWriter().println("<html>" +
                    "                   <head>" +
                    "                       <meta charset='UTF-8'>" +
                    "                       <title>The Pok&eacute;dex Search Results</title>" +
                    "                   </head>" +
                    "                   <body style='background-color:#e7e7e7; color:#4d4d4d;'>" +
                    "                       <h1><u><b>Search Results</b></u></h1><br>");

            String tableString = "<h2>Click the name of the Pok&eacute;mon to see its Pok&eacute;dex entry.<br><br>" +
                    "                                 <table border=2 cellpadding=5 cellspacing=0>" +
                    "                  <tr style='font-weight:bold; background-color:#c7c7c7;'>" +
                    "                       <td>Number</td>" +
                    "                       <td>Name</td>" +
                    "                       <td>Type1</td>" +
                    "                       <td>Type2</td>" +
                    "                       <td>HP</td>" +
                    "                       <td>Atk</td>" +
                    "                       <td>Def</td>" +
                    "                       <td>SAtk</td>" +
                    "                       <td>SDef</td>" +
                    "                       <td>Spd</td>" +
                    "                       <td>Species</td>" +
                    "                       <td>Height</td>" +
                    "                       <td>Weight</td>" +
                    "                  </tr>" +
                    "                  <tr>";
            boolean tablePrinted = false;
            boolean addedToPokeArray = false;
            boolean lineReadContains = false;
            boolean allPrettyTable = false;

            pokeList.clear();
            // This is to make sure the list of Pokemon is clear and free to start adding a fresh list of Pokemon to print.

            while (scanner.hasNext()) {
                lineRead = scanner.next();
                if (lineRead.substring(0, 2) != "ID") lineRead = scanner.next();
                // This is a oneliner saying if the scanner picked up the CSV headers, skip to the next line.
                    if (pokeListed == null || pokeListed.equals(" "))
                        resp.getWriter().println(lineRead.replaceAll("Pok.mon", "Pokémon") + "<br><br>");
                    else if (lineRead.contains(pokeListed)) {
                        // This branch of the conditional will display only the Pokemon that match the search
                        // terms from the user input.
                        lineRead.replaceAll("Pok.mon", "Pokémon");
                        lineRead.replaceAll("Flab.b.", "Flabébé");
                        pokeStringList = lineRead.split(",");
                        addedToPokeArray = true;
                        lineReadContains = true;
                    } else if (pokeListed.equals("allPretty")) {
                        // This has a separate branch in this conditional, because I do not want to print display
                        // the overworld sprite of each 1,000+ Pokemon, but I do want to display the data neatly
                        // in an interactive table.
                        if (!tablePrinted) {
                            resp.getWriter().println(tableString);
                            tablePrinted = true;
                        }
                        lineRead.replaceAll("Pok.mon", "Pokémon");
                        lineRead.replaceAll("Flab.b.", "Flabébé");
                        pokeStringList = lineRead.split(",");
                        addedToPokeArray = true;
                        allPrettyTable = true;
                    }

                if (addedToPokeArray) {
                    // If there was a Pokemon added to the array, continue building the Pokemon into the list.
                    addedToPokeArray = false;
                    // making the boolean false for the next iteration of the while loop.
                    if(pokeStringList[4] == null || pokeStringList[4].equals("")) pokeStringList[4] = "NONE";
                    pokeList.add(new Pokemon(
                            Integer.parseInt(pokeStringList[1]),
                            pokeStringList[2],
                            Type.valueOf(pokeStringList[3].toUpperCase()),
                            Type.valueOf(pokeStringList[4].toUpperCase()),
                            Integer.parseInt(pokeStringList[6]),
                            Integer.parseInt(pokeStringList[7]),
                            Integer.parseInt(pokeStringList[8]),
                            Integer.parseInt(pokeStringList[9]),
                            Integer.parseInt(pokeStringList[10]),
                            Integer.parseInt(pokeStringList[11]),
                            pokeStringList[12],
                            Float.parseFloat(pokeStringList[13].replace(" m", "")),
                            Float.parseFloat(pokeStringList[14].replace(" kg", "")))
                    );
                    if (lineReadContains) {
                        // If the search results contained the string the user inputted in the search field.
                        lineReadContains = false;
                        resp.getWriter().println("<table name='Pokedex Entry' border=0 cellspacing=0 cellpadding=0>" +
                                "                   <tr valign='top'>" +
                                "                       <td><img src='https://www.serebii.net/swordshield/pokemon/"+pokeStringList[1]+".png'></td>" +
                                "                       <td>"+pokeStringList[2]+"</td>");
                    }
                    if (allPrettyTable) {
                        // If showing all Pokemon in a table.
                        allPrettyTable = false;
                        resp.getWriter().println("all Pretty");
                    }
                }
            }
        }
            catch (IOException e) {
                e.printStackTrace();
            }

        // This while loop is necessary, because if there is no Pokemon listed
        // in the path of the URL, then I want each line to print to the server.
        // On the flip side, if there is a Pokemon listed, I want it printed.


    }
}
