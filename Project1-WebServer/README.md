# naoMart Web Server

A lightweight, cross-platform HTTP web server for serving static HTML files from any directory. Built with pure Java.

## Features

✅ Serves HTML, CSS, JavaScript, images, JSON, and other static files  
✅ Cross-platform compatibility (Linux, Windows, macOS)  
✅ Right-click integration for HTML files (Linux)  
✅ GUI launcher with system tray support  
✅ Ultra-fast startup (0.5 seconds)  
✅ Works with any default browser (Chrome, Firefox, Edge, Safari, etc.)  
✅ No configuration needed  
✅ Bundled Java 21 JRE (Linux version)

## Quick Start

### Linux (Bundled Version - Recommended)
```bash
chmod +x install.sh
./install.sh
```

Then right-click any HTML file and select "Open With" → "naoMart"

### All Platforms (Requires Java 21)
```bash
java -jar Server.jar /path/to/your/files
```

Visit `http://localhost:8080/` in your browser.

## Documentation

- 📖 [Installation Guide](INSTALL.md) - Detailed setup for all platforms
- 📦 [Release Information](README-RELEASES.md) - Version history and download options

## Project Structure
```
src/main/java/com/martin/
├── server/
│   ├── ImageUploadServer.java
│   └── Server.java
├── handlers/
│   └── (request handlers)
└── utils/
    └── (utility classes)
```

## Requirements

- **Linux Bundle**: No requirements (Java included)
- **Cross-platform**: Java 21 or higher

## Usage Examples

**Serve from Desktop:**
```bash
java -jar Server.jar ~/Desktop
```

**Serve from Documents:**
```bash
java -jar Server.jar ~/Documents
```

**Serve from any directory:**
```bash
java -jar Server.jar /path/to/directory
```

The server automatically:
- Starts on `http://localhost:8080`
- Opens your default browser
- Serves `index.html` when you visit `/`

## Technical Details

- **Technology**: Pure Java with built-in `HttpServer`
- **Port**: 8080 (default)
- **File serving**: Automatic content-type detection
- **Browser**: Works with any modern browser

## Security Note

This server is designed for local development and serving static files. It should not be exposed to the internet without additional security measures.

## Related Projects

- 📁 [Image Upload Server](../Project2-ImageUploader) - Secure file upload with authentication
- 📁 [View All Projects](../) - Complete internship project collection

## Author

Martin - Backend Intern

## License

This project is for educational purposes.
