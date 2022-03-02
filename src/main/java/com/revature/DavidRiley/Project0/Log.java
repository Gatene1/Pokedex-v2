package com.revature.DavidRiley.Project0;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Log Class will handle the logging aspect of this Pokedex app.
 * CreateLog attempts to create the UserLog.dat file whenever the application is ran.
 * AddToLog will include the file name and the information I want to write to file as its parameters, and will write it.
 */

public class Log {
    public Log(){

    }

    public void CreateLog(){
        File logToCreate = new File("UserLog.dat");
        try {
            logToCreate.createNewFile();
            AddToLog(LocalDateTime.now().toString(), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void AddToLog(String infoToAdd, int newAppend){
        FileWriter fileWriter;
        try {
            if (newAppend == 0)
                fileWriter = new FileWriter("UserLog.dat", false);
            else
                fileWriter = new FileWriter("UserLog.dat", true);

            fileWriter.write(infoToAdd + "\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
