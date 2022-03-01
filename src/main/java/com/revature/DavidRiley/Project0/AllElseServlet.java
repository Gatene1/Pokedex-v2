package com.revature.DavidRiley.Project0;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/** AllElseServlet will handle filenames that are entered into the URL or
 *  when nothing is entered into the URL besides the localhost:8080 URL.
 *  If nothing is entered, then the default file, index.html is loaded
 *  and displayed. If anything other than /pokemon or /search is entered
 *  then the file is loaded and displayed, if exists.
 */

public class AllElseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        String fileName = req.getPathInfo();

        if(fileName == null || fileName.equals("/")) {
            fileName = "index.html";
        }
        else {
            fileName = fileName.replaceFirst("/","");
        }
        InputStream file = getClass().getClassLoader().getResourceAsStream(fileName);

        if (file == null) { file = getClass().getClassLoader().getResourceAsStream("index.html"); }

        try {
            IOUtils.copy(file, resp.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
