package com.revature.DavidRiley.Project0;

import jakarta.servlet.Servlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/** DexML class is responsible for showing the search fields and its tutorial
 *
 */

public class DexML extends HttpServlet {
    public DexML(){

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Log logWriter = new Log();
        try {
            logWriter.AddToLog("Viewed search page", 1);
            resp.getWriter().println("<html>" +
                    "                 <head>" +
                    "                     <title>Welcome to my Pok&eacute;dex!</title>" +
                    "                     <script>" +
                    "                       function ClickedBox() {" +
                    "                           searchBox = document.getElementById('searchBox');" +
                    "                           searchBox.value = '';" +
                    "                       }" +
                    "                       </script>" +
                    "                 </head>" +
                    "                 <body style='color:#4d4d4d; background-color:#e7e7e7;'>" +
                    "                   <br><br><br><br>" +
                    "                   <center>" +
                    "                   <div style='width:50%; align:center; background-color:#e7e7e7; border-style:dashed; border-color:#676767; border-width:5px;'>" +
                    "                       <h1><b><u>&nbsp;&nbsp;Welcome to THE Pok&eacute;dex!&nbsp;&nbsp;</u></b></h1>" +
                    "                       <form name='DexForm' action='pokemon' method='get'>" +
                    "                           <input id='searchBox' type=name name='searchPoke' value='Search ANY Field!' onclick='ClickedBox()'>" +
                    "                           <input type=submit value='Search Through Pok&eacute;mon'>" +
                    "                       </form><br><a href='/pokemon?searchPoke=allPretty'>Show Me the Whole List</a><br><br><br>" +
                    "                   </div>" +
                    "                   <br><br><hr width=50%><br><br><br><br><br><br><br>" +
                    "                   <table name='PokeTable' border=2 cellspacing=0 cellpadding=5>" +
                    "                       <tr align='center'>" +
                    "                           <td>&nbsp;</td>" +
                    "                           <td>Number</td>" +
                    "                           <td>Name</td>" +
                    "                           <td>Type1</td>" +
                    "                           <td>Type2</td>" +
                    "                           <td>HP</td>" +
                    "                           <td>Atk</td>" +
                    "                           <td>Def</td>" +
                    "                           <td>SAtk</td>" +
                    "                           <td>SDef</td>" +
                    "                           <td>Spd</td>" +
                    "                           <td>Species</td>" +
                    "                           <td>Height</td>" +
                    "                           <td>Weight</td>" +
                    "                       </tr>" +
                    "                       <tr align='center'>" +
                    "                           <td><img src='https://www.serebii.net/pokedex-swsh/icon/133.png'></td>" +
                    "                           <td>133</td>" +
                    "                           <td>Eevee</td>" +
                    "                           <td><img src='https://www.serebii.net/pokedex-bw/type/normal.gif'></td>" +
                    "                           <td></td>" +
                    "                           <td>55</td>" +
                    "                           <td>55</td>" +
                    "                           <td>50</td>" +
                    "                           <td>45</td>" +
                    "                           <td>65</td>" +
                    "                           <td>55</td>" +
                    "                           <td>Evolution Pok&eacute;mon</td>" +
                    "                           <td>0.3 m</td>" +
                    "                           <td>6.5 kg</td>" +
                    "                       </tr>" +
                    "                   </table><br><br>" +
                    "                   <table name='ShowPoke' border=0 cellspacing=0 cellpadding=0>" +
                    "                   <tr valign='top'>" +
                    "                       <td><img src='https://www.serebii.net/swordshield/pokemon/133.png' align='left'></td>" +
                    "                       <td>" +
                    "                           <font size='7'><b>#133 Eevee</b></font><br>" +
                    "                           <font size='5'>HT. 0.3 m, WT. 6.5 kg<br><br>"+
                    "                           <img src='https://www.serebii.net/pokedex-bw/type/normal.gif' style='height:25'><br><br>" +
                    "                           <i>Evolution Pok&eacute;mon</i></font></td>" +
                    "                   </tr>" +
                    "               </table>" +
                    "               <table border=2 cellspacing=0 cellpadding=10>" +
                    "                   <tr style='font-weight:bold; font-size:24pt; background-color:#d7d7d7' align='center'>" +
                    "                       <td>HP</td>" +
                    "                       <td>Atk</td>" +
                    "                       <td>Def</td>" +
                    "                       <td>SAtk</td>" +
                    "                       <td>SDef</td>" +
                    "                       <td>Spd</td>" +
                    "                   </tr>" +
                    "                   <tr style='font-size:24pt;' align='center'>" +
                    "                       <td>55</td>" +
                    "                       <td>55</td>" +
                    "                       <td>50</td>" +
                    "                       <td>45</td>" +
                    "                       <td>65</td>" +
                    "                       <td>55</td>"+
                    "               </center></body></head></html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
