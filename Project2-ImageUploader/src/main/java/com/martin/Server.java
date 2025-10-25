package com.martin;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Server {

    private static final int PORT = 8080;
    private static String WEB_ROOT = "public";

    // If a directory is passed as argument, use it; otherwise use default
    public Server(String[] directory) throws IOException {
       if (directory.length > 0) {
           WEB_ROOT = directory[0];
       }
       HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);

       server.createContext("/", new FileHandler());
       server.setExecutor(null);
       server.start();

       System.out.println("Server started on http://localhost:" + PORT);
       new FileHandler();
   }

   private static class FileHandler implements HttpHandler {

       @Override
       public void  handle(HttpExchange exchange) throws IOException {
           String path = exchange.getRequestURI().getPath();

           if (path.equals("/")) path = "/index.html";

           String filePath = WEB_ROOT + path;

           File file = new File(filePath);

           if (file.exists() && !file.isDirectory()) {
               byte[] bytes = Files.readAllBytes(Paths.get(filePath));

               exchange.getResponseHeaders().set("Content-Type", getContentType(filePath));
               exchange.sendResponseHeaders(200, bytes.length);

               OutputStream stream = exchange.getResponseBody();
               stream.write(bytes);
               stream.close();
           }
           else {
               String response = "404 - File Not Found";
               exchange.sendResponseHeaders(404, response.length());
               OutputStream stream = exchange.getResponseBody();
               stream.write(response.getBytes());
               stream.close();
           }
       }
   }

   private static String getContentType(String filePath) {
       if (filePath.endsWith(".html")) return "text/html";
       if (filePath.endsWith(".css")) return "text/css";
       if (filePath.endsWith(".js")) return "application/javascript";
       if (filePath.endsWith(".jpg") || filePath.endsWith(".jpeg")) return "image/jpeg";
       if (filePath.endsWith(".png")) return "image/png";
       if (filePath.endsWith(".gif")) return "image/gif";
       if (filePath.endsWith(".json")) return "application/json";
       if (filePath.endsWith(".txt")) return "text/plain";
       return "text/plain";
   }
}
