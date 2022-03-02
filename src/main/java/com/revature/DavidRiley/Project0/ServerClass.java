package com.revature.DavidRiley.Project0;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

/** ServerClass will handle each operation related to the HTTP server created for user interaction.
 *
 */

public class ServerClass {
    public ServerClass(){
        // Just the constructor.
    }

    public void BeginServer(){
        AllElseServlet allElseServlet = new AllElseServlet();
        Tomcat server = new Tomcat();
        server.setBaseDir("java.io.tmpdir");
        server.setPort(8080);
        server.getConnector();
        server.addContext("",null);
        server.addServlet("", "Default", allElseServlet).addMapping("/*");
        server.addServlet("", "Default2", allElseServlet).addMapping("");
        server.addServlet("","DexSearch", new DexSearch()).addMapping("/pokemon");
        server.addServlet("","DexML", new DexML()).addMapping("/search");
        server.addServlet("","LoggingFeatures", new LogPrint()).addMapping("/log");

        try {
            server.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }
}
