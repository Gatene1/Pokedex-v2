package com.revature.DavidRiley.Project0;

/**
 * The main class of my Pokedex application will start my logging system, and the Tomcat Server.
 */

public class App {

    public static void main(String[] args){
        Log logClass = new Log();
        logClass.CreateLog();
        ServerClass serverClass = new ServerClass();
        serverClass.BeginServer();

    }
}
