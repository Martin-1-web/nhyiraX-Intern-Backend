package com.martin;

import java.io.*;
import java.net.ServerSocket;

public class WebServer {
    public  static void main(String[] args) {

        try {

            var server = new ServerSocket(8080);
            System.out.println("Server started at http://localhost:8080");

            while (true) {
                var client = server.accept();

                var reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                var readLine = reader.readLine();
                System.out.println("Request: " + readLine);

                var response = "<html> <head><title>Web Server</title></head> <body><h1>Hello there,</h1> <p>This is a simple web page served by Java program.</p>  </body </html>";

                PrintWriter out = new PrintWriter(client.getOutputStream());
                out.println("HTTP/1.1 200 OK");
                out.println("Content-Type: text/html");
                out.println("Content-Length: " + response.length());
                out.println();

                out.println(response);

                out.flush();

                client.close();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
