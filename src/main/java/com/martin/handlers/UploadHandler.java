package com.martin.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;

public class UploadHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // CHECK IF USER IS LOGGED IN
        String cookie = exchange.getRequestHeaders().getFirst("Cookie");
        String sessionId = null;

        if (cookie != null && cookie.contains("session=")) {
            sessionId = cookie.split("session=")[1].split(";")[0];
        }

        String userName = LoginHandler.getUserFormSession(sessionId);

        if (userName == null) {
            // NOT LOGGED IN - REDIRECT TO LOGIN
            String response = "Please <a href='/login'>Login</a> first";
            exchange.getResponseHeaders().set("Content-Type", "text/html");
            try {
                exchange.sendResponseHeaders(401, response.length());
                OutputStream outputStream = exchange.getResponseBody();
                outputStream.write(response.getBytes());
                outputStream.close();
                return;
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        if (exchange.getRequestMethod().equals("GET")) {
            // SHOW UPLOAD FORM
            String html = "<!DOCTYPE html>" +
                    "<html>" +
                    "<body>" +
                    "<h1>Upload Image (Max 500KB)</h1>" +
                    "<p>Welcome, " + userName + "!</p>" +
                    "<form method='POST' action='/upload' enctype='multipart/form-data'>" +
                    "<input type='file' name='image' accept='image/*' required><br><br>" +
                    "<button type='submit'>Upload</button>" +
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

        else if (exchange.getRequestMethod().equals("POST")) {
            // HANDLE FILE UPLOAD
            String contentType = exchange.getRequestHeaders().getFirst("Content-Type");
            String boundary = contentType.split("boundary=")[1];

            InputStream inputStream = exchange.getRequestBody();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            byte[] data = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(data)) != -1) {
                buffer.write(data, 0, bytesRead);
            }

            byte[] requestBody = buffer.toByteArray();

            // CHECK FILE SIZE (MAX 500KB - 512000 bytes)
            if (requestBody.length > 512000) {
                String response = "File too large! Maximum size is 500KB. <a href='/upload'>Try again</a>";
                exchange.getResponseHeaders().set("Content-Type", "text/html");
                exchange.sendResponseHeaders(400, response.length());
                OutputStream outputStream = exchange.getResponseBody();
                outputStream.write(response.getBytes());
                outputStream.close();
                return;
            }

            // PARSE MULTIPART DATA
            String bodyString = new String(requestBody);
            String[] parts = bodyString.split("--" + boundary);

            byte[] fileData = null;
            String fileName = "";

            for (String part : parts) {
                if (part.contains("Content-Disposition") && part.contains("filename=")) {
                    // EXTRACT FILENAME
                    String[] lines = part.split("\r\n");
                    for(String line : lines) {
                        if (line.contains("filename=")) {
                            fileName = line.split("filename=\"")[1].split("\"")[0];
                            break;
                        }
                    }
                }

                // EXTRACT FILE DATA
                int dataStart = part.indexOf("\r\n\r\n") + 4;

                if (dataStart > 3) {
                    // Find where this part starts in the original body
                    int partStartInBody = bodyString.indexOf(part);
                    int fileStartInBody = partStartInBody + dataStart;

                    // Find the next boundary to know where file ends
                    int fileEndInBody = -1;
                    for (int i = 1; i < parts.length; i++) {
                        if (parts[i].startsWith("--") || parts[i].contains("Content-Disposition")) {
                            fileEndInBody = bodyString.indexOf("--" + boundary, fileStartInBody);
                            break;
                        }
                    }

                    if (fileEndInBody > fileStartInBody) {
                        // Subtract the \r\n before boundary
                        fileEndInBody -= 2;
                        int fileLength = fileEndInBody - fileStartInBody;
                        fileData = new byte[fileLength];
                        System.arraycopy(requestBody, fileStartInBody, fileData, 0, fileLength);
                    }
                }
            }

            // VALIDATE FILE TYPE (MUST BE IMAGE)
            if (!fileName.toLowerCase().endsWith(".jpg") && !fileName.toLowerCase().endsWith(".jpeg") &&
                    !fileName.toLowerCase().endsWith(".png") && !fileName.toLowerCase().endsWith(".gif")) {

                String response = "Invalid file type! Please upload an image (jpg, jpeg, png, gif). <a href='/upload'>Try again</a>";
                exchange.getResponseHeaders().set("Content-Type", "text/html");
                exchange.sendResponseHeaders(400, response.length());
                OutputStream outputStream = exchange.getResponseBody();
                outputStream.write(response.getBytes());
                outputStream.close();
                return;
            }

            // SAVE FILE
            if (fileData != null && !fileName.isEmpty()) {
                // Validate actual file content (magic numbers)
                if (!isValidImage(fileData)) {
                    String response = "Invalid image file! File content doesn't match image format. <a href='/upload'>Try again</a>";
                    exchange.getResponseHeaders().set("Content-Type", "text/html");
                    exchange.sendResponseHeaders(400, response.length());
                    OutputStream outputStream = exchange.getResponseBody();
                    outputStream.write(response.getBytes());
                    outputStream.close();
                    return;
                }
                // CREATE USER DIRECTORY
                String userDir = "uploads" + File.separator + userName;
                new File(userDir).mkdirs();

                // SAVE FILE WITH UNIQUE NAME
                String saveFileName = System.currentTimeMillis() + "_" + fileName;
                FileOutputStream fos = new FileOutputStream(userDir + File.separator + saveFileName);
                fos.write(fileData);
                fos.close();

                String response = "Image uploaded successfully!<br>" +
                        "File: " + fileName + "<br>" + "Size: " + fileData.length + " bytes<br>" +
                        "<a href='/upload'>Upload another</a>";

                exchange.getResponseHeaders().set("Content-Type", "text/html");
                exchange.sendResponseHeaders(200, response.length());
                OutputStream outputStream = exchange.getResponseBody();
                outputStream.write(response.getBytes());
                outputStream.close();
            }
            else {
                String response = "No file uploaded. <a href='/upload'>Try again</a>";
                exchange.getResponseHeaders().set("Content-Type", "text/html");
                exchange.sendResponseHeaders(400, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }

    private boolean isValidImage(byte[] fileData) {
        if (fileData == null || fileData.length < 4) {
            return false;
        }

        // Check file signatures (magic numbers)
        // JPEG: FF D8 FF
        if (fileData[0] == (byte) 0xFF && fileData[1] == (byte) 0xD8 && fileData[2] == (byte) 0xFF) {
            return true;
        }

        // PNG: 89 50 4E 47
        if (fileData[0] == (byte) 0x89 && fileData[1] == (byte) 0x50 &&
                fileData[2] == (byte) 0x4E && fileData[3] == (byte) 0x47) {
            return true;
        }

        // GIF: 47 49 46
        if (fileData[0] == (byte) 0x47 && fileData[1] == (byte) 0x49 && fileData[2] == (byte) 0x46) {
            return true;
        }

        return false;
    }
}
