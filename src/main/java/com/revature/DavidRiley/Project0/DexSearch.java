package com.revature.DavidRiley.Project0;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
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
        String pokeParam = req.getParameter("searchPoke");
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
                    "                       <style>" +
                    "                           a {color:#000000;}" +
                    "                           a:hover {font-weight:bold;}" +
                    "                           .highlight:hover {background-color:#d7d7ff;}" +
                    "                       </style>" +
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
                    "                  </tr>";
            boolean tablePrinted = false;
            boolean addedToPokeArray = false;
            boolean lineReadContains = false;
            boolean allPrettyTable = false;

            pokeList.clear();
            // This is to make sure the list of Pokemon is clear and free to start adding a fresh list of Pokemon to print.

            while (scanner.hasNext()) {
                lineRead = scanner.next();
                if (lineRead.substring(0, 2).equals("ID")) lineRead = scanner.next();
                    // This is a oneliner saying if the scanner picked up the CSV headers, skip to the next line.
                if (pokeParam == null || pokeParam.equals(" ") || pokeParam.equals("")) {
                    resp.getWriter().println(lineRead  + "<br><br>");
                }
                else if (lineRead.contains(pokeParam) && !pokeParam.equals("allPretty")) {
                    // This branch of the conditional will display only the Pokemon that match the search
                    // terms from the user input.
                    lineRead.replaceAll("Pok.mon", "Pokémon");
                    lineRead.replaceAll("Flab.b.", "Flabébé");
                    pokeStringList = lineRead.split(",");
                    addedToPokeArray = true;
                    lineReadContains = true;
                } else if (pokeParam.equals("allPretty")) {
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
                            pokeStringList[13],
                            pokeStringList[14])
                    );
                    int pokeCount = pokeList.size() - 1;
                    if (lineReadContains && !allPrettyTable) {
                        // If the search results contained the string the user inputted in the search field.
                        lineReadContains = false;
                        String pokeID = AddZeroesToID(pokeList.get(pokeCount).id());
                        String pokeImg = AddImageSuffix(pokeList.get(pokeCount).name(), pokeID);
                        resp.getWriter().println("<table name='Pokedex Entry' border=0 cellspacing=5 cellpadding=0>" +
                                "                   <tr valign='top'>" +
                                "                       <td><img src='https://www.serebii.net/"+pokeImg+"'></td>" +
                                "                       <td><font size='7'><b>"+pokeList.get(pokeCount).name().replaceAll("Flab.b.", "Flabébé")+
                                "                       #"+pokeID+"</b></font><br>" +
                                "                           <font size='5'>HT "+pokeList.get(pokeCount).ht()+", WT " + pokeList.get(pokeCount).wt()+"<br><br>" +
                                "                           "+GetTypeImageName(pokeList.get(pokeCount).type1())+"&nbsp;&nbsp;&nbsp;" +
                                "                           "+GetTypeImageName(pokeList.get(pokeCount).type2())+"<br><br>" +
                                        "                   <i>"+pokeList.get(pokeCount).species().replaceAll("Pok.mon","Pokémon")+"</i>" +
                                "                       </td>"+
                                "                   </tr>" +
                                "               </table>" +
                                "               <table border=2 cellpadding=10 cellspacing=0>" +
                                "                   <tr style='font-weight:bold; font-size:24pt; background-color:#d7d7d7; align:center'>" +
                                "                       <td>HP</td>" +
                                "                       <td>Atk</td>" +
                                "                       <td>Def</td>" +
                                "                       <td>SAtk</td>" +
                                "                       <td>SDef</td>" +
                                "                       <td>Spd</td>" +
                                "                   </tr>" +
                                "                   <tr style='font-size:24pt; align:center'>" +
                                "                       <td>"+ pokeList.get(pokeCount).hp()+"</td>" +
                                "                       <td>"+ pokeList.get(pokeCount).atk()+"</td>" +
                                "                       <td>"+ pokeList.get(pokeCount).def()+"</td>" +
                                "                       <td>"+ pokeList.get(pokeCount).sAtk()+"</td>" +
                                "                       <td>"+ pokeList.get(pokeCount).sDef()+"</td>" +
                                "                       <td>"+ pokeList.get(pokeCount).spd()+"</td>" +
                                "                   </tr>" +
                                "               </table><br><br>");
                    } else if (allPrettyTable && tablePrinted) {
                        // If showing all Pokemon in a table.
                        allPrettyTable = false;
                        resp.getWriter().println("<tr class='highlight'>" +
                                "                   <td>"+AddZeroesToID(pokeList.get(pokeCount).id())+"</td>" +
                                "                   <td><a href='/pokemon?searchPoke="+pokeList.get(pokeCount).id()+","+pokeList.get(pokeCount).name()+"'>"+pokeList.get(pokeCount).name()+"</a></td>" +
                                "                   <td>"+pokeList.get(pokeCount).type1()+"</td>" +
                                "                   <td>"+pokeList.get(pokeCount).type2()+"</td>" +
                                "                   <td>"+pokeList.get(pokeCount).hp()+"</td>" +
                                "                   <td>"+pokeList.get(pokeCount).atk()+"</td>" +
                                "                   <td>"+pokeList.get(pokeCount).def()+"</td>" +
                                "                   <td>"+pokeList.get(pokeCount).sAtk()+"</td>" +
                                "                   <td>"+pokeList.get(pokeCount).sDef()+"</td>" +
                                "                   <td>"+pokeList.get(pokeCount).spd()+"</td>" +
                                "                   <td>"+pokeList.get(pokeCount).species()+"</td>" +
                                "                   <td>"+pokeList.get(pokeCount).ht()+"</td>" +
                                "                   <td>"+pokeList.get(pokeCount).wt()+"</td>" +
                                "               </tr>");
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

    private String AddZeroesToID(int id){
        // This will take the Pokemon's ID number and convert it into a string that will match the image
        // name on the Website, to display.
        String idConv = Integer.toString(id);
        switch (idConv.length()) {
            case 1:
                idConv = "00"+id;
                break;
            case 2:
                idConv = "0"+id;
                break;
            case 3:
                break;
        }
        return idConv;
    }

    private String AddImageSuffix(String pokemonName, String id){
        if(pokemonName.contains("(Mega")) {
            if(pokemonName.contains("Charizard X"))
                pokemonName = "xy/pokemon/"+id+"-mx.png";
            else if(pokemonName.contains("Charizard Y"))
                pokemonName = "xy/pokemon/"+id+"-my.png";
            else if(pokemonName.contains("Mewtwo X"))
                pokemonName = "xy/pokemon/"+id+"-my.png";
            else if(pokemonName.contains("Mewtwo Y"))
                pokemonName = "xy/pokemon/"+id+"-my.png";
            else
                pokemonName = "xy/pokemon/"+id + "-m.png";
        }
        else if(pokemonName.contains("(Alolan")) {
            pokemonName = "swordshield/pokemon/"+id + "-a.png";
        }
        else if(pokemonName.contains("(Galarian")) {
            pokemonName = "swordshield/pokemon/"+id + "-g.png";
        }
        else if(pokemonName.contains("Partner Pikachu")){
            pokemonName = "swordshield/pokemon/"+id + "-o.png";
        }
        else {
            pokemonName = "swordshield/pokemon/"+id+".png";
        }
        return pokemonName;
    }

    private String GetTypeImageName(Enum<Type> type){
        String typeValue = type.name();
        String imgURL = "<img style='height:25' src='https://www.serebii.net/pokedex-bw/type/";
        switch (typeValue) {
            case "BUG":
                imgURL = imgURL + "bug.gif'>";
            break;
            case "DARK":
                imgURL = imgURL + "dark.gif'>";
            break;
            case "DRAGON":
                imgURL = imgURL + "dragon.gif'>";
            break;
            case "ELECTRIC":
                imgURL = imgURL + "electric.gif'>";
                break;
            case "FAIRY":
                imgURL = imgURL + "fairy.gif'>";
                break;
            case "FIGHTING":
                imgURL = imgURL + "fighting.gif'>";
                break;
            case "FIRE":
                imgURL = imgURL + "fire.gif'>";
                break;
            case "FLYING":
                imgURL = imgURL + "flying.gif'>";
                break;
            case "GHOST":
                imgURL = imgURL + "ghost.gif'>";
                break;
            case "GRASS":
                imgURL = imgURL + "grass.gif'>";
                break;
            case "GROUND":
                imgURL = imgURL + "ground.gif'>";
                break;
            case "ICE":
                imgURL = imgURL + "ice.gif'>";
                break;
            case "NORMAL":
                imgURL = imgURL + "normal.gif'>";
                break;
            case "POISON":
                imgURL = imgURL + "poison.gif'>";
                break;
            case "PSYCHIC":
                imgURL = imgURL + "psychic.gif'>";
                break;
            case "ROCK":
                imgURL = imgURL + "rock.gif'>";
                break;
            case "STEEL":
                imgURL = imgURL + "steel.gif'>";
                break;
            case "WATER":
                imgURL = imgURL + "water.gif'>";
                break;
            default:
                imgURL = "";
                break;
        }
        return imgURL;
    }
}
