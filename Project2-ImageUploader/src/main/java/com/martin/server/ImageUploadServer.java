package com.martin.server;

import com.martin.handlers.HomeHandler;
import com.martin.handlers.LoginHandler;
import com.martin.handlers.RegisterHandler;
import com.martin.handlers.UploadHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ImageUploadServer {
    private static final int PORT = 8081;
    private HttpServer server;

    public ImageUploadServer() {
        try {
            server = HttpServer.create(new InetSocketAddress(PORT), 0);
            server.createContext("/", new HomeHandler());
            server.createContext("/register", new RegisterHandler());
            server.createContext("/login", new LoginHandler());
            server.createContext("/upload", new UploadHandler());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        start();
    }

    private void start() {
        server.setExecutor(null);
        server.start();
        System.out.println("Server started at http://localhost:" + PORT);
    }

    public static void main(String[] args) {
        new ImageUploadServer();
    }
}
