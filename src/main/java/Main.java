import java.io.*;
import java.net.*;

public class Main {

    public static void main(String[] args) {
        int port = 8080;

        try {
            // Create a server socket on port 8080
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("🚀 Server started on http://localhost:" + port);
            System.out.println("Press Ctrl+C to stop the server");

            // Keep server running forever
            while (true) {
                // Wait for a client to connect
                Socket clientSocket = serverSocket.accept();
                System.out.println("✅ Client connected: " + clientSocket.getInetAddress());

                // Handle the request
                handleRequest(clientSocket);
            }

        } catch (IOException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }

    private static void handleRequest(Socket clientSocket) {
        try {
            // Read request from client
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream())
            );

            // Read the first line (GET /path HTTP/1.1)
            String requestLine = in.readLine();
            System.out.println("📥 Request: " + requestLine);

            // Skip the rest of the headers
            String line;
            while ((line = in.readLine()) != null && !line.isEmpty()) {
                // Just reading headers, not using them
            }

            // Prepare response
            String response = buildResponse();

            // Send response to client
            OutputStream out = clientSocket.getOutputStream();
            out.write(response.getBytes());
            out.flush();

            // Close connection
            clientSocket.close();
            System.out.println("📤 Response sent, connection closed\n");

        } catch (IOException e) {
            System.err.println("❌ Error handling request: " + e.getMessage());
        }
    }

    private static String buildResponse() {
        // HTML content
        String htmlContent = """
            <!DOCTYPE html>
            <html>
            <head>
                <title>Simple Java Server</title>
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        max-width: 600px;
                        margin: 50px auto;
                        padding: 20px;
                        background: #f0f0f0;
                    }
                    .container {
                        background: white;
                        padding: 30px;
                        border-radius: 10px;
                        box-shadow: 0 2px 10px rgba(0,0,0,0.1);
                    }
                    h1 { color: #333; }
                    p { color: #666; line-height: 1.6; }
                    .success { color: #28a745; font-weight: bold; }
                </style>
            </head>
            <body>
                <div class="container">
                    <h1>🎉 Success!</h1>
                    <p class="success">Your Java web server is running!</p>
                    <p>This page is served by a simple Java program using only:</p>
                    <ul>
                        <li>ServerSocket (to listen for connections)</li>
                        <li>Socket (to handle client connections)</li>
                        <li>BufferedReader (to read requests)</li>
                        <li>OutputStream (to send responses)</li>
                    </ul>
                    <p><strong>No frameworks. No dependencies. Pure Java!</strong></p>
                </div>
            </body>
            </html>
            """;

        // HTTP response with headers
        String httpResponse = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-Length: " + htmlContent.length() + "\r\n" +
                "Connection: close\r\n" +
                "\r\n" +
                htmlContent;

        return httpResponse;
    }
}