package com.revature.DavidRiley.Project0;

import java.util.Map;

/**
 * The main class of my Pokedex application will start my logging system, and the Tomcat Server.
 */

public class App {

    public static void main(String[] args){
         for (String env: args){
            String value = System.getenv(env);
            if (value != null) {
                System.out.format("%s=%s%n", env, value);
            } else {
                System.out.format("%s is not assigned.%n", env);
            }
        }

        Log logClass = new Log();
        logClass.CreateLog();
        ServerClass serverClass = new ServerClass();
        serverClass.BeginServer();

    }
}
