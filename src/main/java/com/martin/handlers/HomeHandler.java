package com.martin.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class HomeHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String html = "<!DOCTYPE html>" +
                "<html>" +
                "<head><title>Image Upload Server</title></head>" +
                "<body>" +
                "<h1>Welcome to Image Upload Server</h1>" +
                "<p>A simple server for uploading images (max 500KB)</p>" +
                "<h3>Get Started:</h3>" +
                "<ul>" +
                "<li><a href='/register'>Register</a> - Create a new account</li>" +
                "<li><a href='/login'>Login</a> - Sign in to your account</li>" +
                "<li><a href='/upload'>Upload</a> - Upload an image (requires login)</li>" +
                "</ul>" +
                "</body>" +
                "</html>";

        exchange.getResponseHeaders().set("Content-Type", "text/html");
        exchange.sendResponseHeaders(200, html.length());
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(html.getBytes());
        outputStream.close();

    }
}
