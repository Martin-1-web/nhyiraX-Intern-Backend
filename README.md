# Java Web Server

A lightweight, cross-platform HTTP web server built with pure Java. Serve static files (HTML, CSS, JS, images) from any directory on your computer—just like opening files in a web browser.

## Features

✅ **Cross-Platform** - Works on Linux, Windows, and macOS  
✅ **Zero Dependencies** - Uses only Java's built-in `HttpServer`  
✅ **Simple to Use** - Point to any directory and serve files  
✅ **Multiple File Types** - HTML, CSS, JavaScript, images, JSON, and more  
✅ **Auto Index** - Serves `index.html` when visiting root `/`  
✅ **Portable** - Single JAR file, easy to share  

## Requirements

- Java 8 or higher
- Download from: https://www.oracle.com/java/technologies/downloads/

## Quick Start

### Using the JAR file (easiest)

1. Download `Server.jar` from releases
2. Run it with any directory:
   ```bash
   java -jar Server.jar /path/to/your/files
   ```
3. Open your browser and go to: `http://localhost:8080/`

### Examples

**Serve files from Desktop:**
```bash
java -jar Server.jar ~/Desktop
```

**Serve files from Documents:**
```bash
java -jar Server.jar ~/Documents
```

**Serve files from any directory:**
```bash
java -jar Server.jar /path/to/your/files
```

## Building from Source

1. Clone the repository:
   ```bash
   git clone https://github.com/Martin-1-web/nhyiraX-Intern-Backend.git
   cd nhyiraX-Intern-Backend
   ```

2. Navigate to the source directory:
   ```bash
   cd src/main/java
   ```

3. Compile the code:
   ```bash
   javac com/martin/WebServer.java com/martin/Server.java
   ```

4. Create the JAR file:
   ```bash
   jar cfe Server.jar com.martin.WebServer com/martin/*.class
   ```

5. Run it:
   ```bash
   java -jar Server.jar /path/to/your/files
   ```

## Usage

1. **Place your HTML files** in any directory (Desktop, Documents, etc.)
2. **Run the server:**
   ```bash
   java -jar Server.jar /path/to/files
   ```
3. **Open your browser** to `http://localhost:8080/`
4. **Access your files:**
   - `http://localhost:8080/` → serves `index.html`
   - `http://localhost:8080/page.html` → serves `page.html`
   - `http://localhost:8080/style.css` → serves `style.css`

## Supported File Types

- HTML (`.html`)
- CSS (`.css`)
- JavaScript (`.js`)
- Images (`.jpg`, `.jpeg`, `.png`, `.gif`)
- JSON (`.json`)
- Text files (`.txt`)

## Default Port

The server runs on **port 8080** by default. Access it at `http://localhost:8080/`

## Stopping the Server

Press `Ctrl+C` in the terminal where the server is running.

## Project Structure

```
src/main/java/com/martin/
├── WebServer.java       (Main entry point)
└── Server.java          (HTTP server and request handler)
```

## How It Works

- **WebServer.java** - Contains the `main` method that starts the server
- **Server.java** - Handles HTTP requests and serves files based on their type
- Automatically detects file types and sets correct `Content-Type` headers
- Returns 404 error if file is not found

## License

This project is open source and available for personal and commercial use.

## Author

Martin

---

**Enjoy your lightweight web server!**
