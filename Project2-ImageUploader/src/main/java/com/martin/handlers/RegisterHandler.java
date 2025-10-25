package com.martin.handlers;

import com.martin.utils.UserManager;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URLDecoder;

public class RegisterHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        if (exchange.getRequestMethod().equals("GET")) {
            String html = "<!DOCTYPE html>" +
                    "<html>" +
                    "<body>" +
                    "<h1>Register</h1>" +
                    "<form method='POST' action='/register'>" +
                        "<input type='text' name='username' placeholder='Username' required><br><br>" +
                        "<input type='password' name='password' placeholder='Password' required><br><br>" +
                        "<button type='submit'>Register</button>" +
                    "</form>" +
                    "</body>" +
                    "</html>";

            exchange.getResponseHeaders().set("Content-Type", "text/html");
            try {
                exchange.sendResponseHeaders(200, html.length());
                OutputStream outputStream = exchange.getResponseBody();
                outputStream.write(html.getBytes());
                outputStream.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        else if(exchange.getRequestMethod().equals("POST")) {

            // Read form data
            InputStream inputStream = exchange.getRequestBody();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            String formData = content.toString();
            String[] pairs = formData.split("&");
            String username = "";
            String password = "";

            for(String pair : pairs) {
                String[] kv = pair.split("=");
                if (kv[0].equals("username")) {
                    username = URLDecoder.decode(kv[1], "UTF-8");
                }
                if (kv[0].equals("password")) {
                    password = URLDecoder.decode(kv[1], "UTF-8");
                }
            }
            // Register user
            if (UserManager.registerUser(username, password)) {
                String response = "Registration successful! <a href='/login'>Login here</a>";
                exchange.getResponseHeaders().set("Content-Type", "text/html");
                exchange.sendResponseHeaders(200, response.length());
                OutputStream outputStream = exchange.getResponseBody();
                outputStream.write(response.getBytes());
                outputStream.close();
            }
            else {
                String response = "User name already exists!";
                exchange.getResponseHeaders().set("Content-Type", "text/html");
                exchange.sendResponseHeaders(400, response.length());
                OutputStream outputStream = exchange.getResponseBody();
                outputStream.write(response.getBytes());
                outputStream.close();
            }
        }
    }
}
