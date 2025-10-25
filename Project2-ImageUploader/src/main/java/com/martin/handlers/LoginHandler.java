package com.martin.handlers;

import com.martin.utils.UserManager;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LoginHandler implements HttpHandler {

    private static Map<String, String> sessions = new HashMap<>();

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        if (exchange.getRequestMethod().equals("GET")) {
            String html = "<!DOCTYPE html>" +
                    "<html>" +
                    "<body>" +
                    "<h1>Login</h1>" +
                    "<form method='POST' action='/login'>" +
                    "<input type='text' name='username' placeholder='Username' required><br><br>" +
                    "<input type='password' name='password' placeholder='Password' required><br><br>" +
                    "<button type='submit'>Login</button>" +
                    "</form>" +
                    "<p>Don't have an account? <a href='/register'>Register here</a></p>" +
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
        else if (exchange.getRequestMethod().equals("POST")) {
            // READ FORM DATA
            InputStream inputStream = exchange.getRequestBody();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            String formData = content.toString();
            String[] parts = formData.split("&");
            String username = "";
            String password = "";

            for(String pair : parts) {
                String[] kv = pair.split("=");
                if (kv[0].equals("username")) {
                    username = URLDecoder.decode(kv[1], "UTF-8");
                }
                if (kv[0].equals("password")) {
                    password = URLDecoder.decode(kv[1], "UTF-8");
                }
            }
            // VALIDATE LOGIN
            if (UserManager.validateLogin(username, password)) {
                String sessionId = UUID.randomUUID().toString();
                sessions.put(sessionId, username);

                String response = "Login successful! <a href='/upload'>Upload image</a>";
                exchange.getResponseHeaders().set("Content-Type", "text/html");
                exchange.getResponseHeaders().set("Set-Cookie", "session=" + sessionId);
                exchange.sendResponseHeaders(200, response.length());
                OutputStream outputStream = exchange.getResponseBody();
                outputStream.write(response.getBytes());
                outputStream.close();
            }

            else {
                String response = "Invalid user name or password!";
                exchange.getResponseHeaders().set("Content-Type", "text/html");
                exchange.sendResponseHeaders(401, response.length());
                OutputStream outputStream = exchange.getResponseBody();
                outputStream.write(response.getBytes());
                outputStream.close();
            }
        }
    }

    public static String getUserFormSession(String sessionId) {
        return sessions.get(sessionId);
    }
}
