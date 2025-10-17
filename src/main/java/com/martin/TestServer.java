package com.martin;

import java.io.*;

public class TestServer {
    public static void main(String[] args) {

        String path;
        // User opened a specific .html file
        if (args.length > 0 && args[0].endsWith(".html")) {
            File htmlFile = new File(args[0]);
            path = htmlFile.getParent();
            System.setProperty("start.file", "/" + htmlFile.getName());
        }

        else {
            path = args.length > 0 ? args[0] : "";
            System.setProperty("start.file", "/index.html");
        }
        //new Server(path);
        //new Server("/home/martin/Desktop");

    }
}
