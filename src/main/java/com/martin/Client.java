package com.martin;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {

    private static final int PORT = 8080;
    private static File rootFolder;

    public Client(String folderPath) {
        // If folder path is not provided, use current directory
        if (folderPath == null || folderPath.isEmpty()) {
            rootFolder = new File(System.getProperty("user.dir"));
        } else {
            rootFolder = new File(folderPath);
        }

        System.out.println("Serving files from: " + rootFolder.getAbsolutePath());
        startServer();
    }

    private void startServer() {
        System.out.println("Server started at http://localhost:" + PORT);

        try (ServerSocket server = new ServerSocket(PORT)) {

            while (true) {
                try (Socket connection = server.accept()) {

                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String requestLine = reader.readLine();
                    System.out.println("Request: " + requestLine);

                    if (requestLine == null) continue;

                    // Parse file name from request (like "GET /index.html HTTP/1.1")
                    String[] parts = requestLine.split(" ");
                    String fileName = parts.length > 1 ? parts[1] : "/";

                    if (fileName.equals("/")) {
                        fileName = "/index.html";
                    }

                    File file = new File(rootFolder, fileName);

                    if (file.exists() && file.isFile()) {
                        sendResponse(connection, file);
                    } else {
                        sendNotFound(connection);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void sendResponse(Socket connection, File file) throws IOException {
        OutputStream output = connection.getOutputStream();
        PrintWriter out = new PrintWriter(output);

        StringBuilder content = new StringBuilder();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = fileReader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }

        out.println("HTTP/1.1 200 OK");
        out.println("Content-Type: text/html");
        out.println("Content-Length: " + content.length());
        out.println();
        out.println(content);
        out.flush();
        output.close();
    }

    private void sendNotFound(Socket connection) throws IOException {
        OutputStream output = connection.getOutputStream();
        PrintWriter out = new PrintWriter(output);

        String response = "<h1>404 Not Found</h1>";
        out.println("HTTP/1.1 404 Not Found");
        out.println("Content-Type: text/html");
        out.println("Content-Length: " + response.length());
        out.println();
        out.println(response);
        out.flush();
        output.close();
    }
}
