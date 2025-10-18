# naoMart - HTTP Server Launcher

A lightweight, cross-platform HTTP web server with GUI launcher. Right-click any HTML file and open it with naoMart!

## Installation

### Linux

1. Download and extract this package
2. Run the installer:
```bash
   chmod +x install-webserver.sh
   ./install-webserver.sh
```

3. **Done!** You can now:
   - Right-click any HTML file and select "Open With" → "naoMart"
   - Or search for "naoMart" in your applications menu

### Windows & macOS

Download the main `Server.jar` and run:
```bash
java -jar Server.jar /path/to/your/files
```

## Usage

**Method 1: Right-click HTML file (Linux)**
- Right-click any HTML file
- Select "Open With" → "naoMart"
- Server starts and default browser opens automatically

**Method 2: Command line (All platforms)**
```bash
java -jar Server.jar ~/Desktop
```

Then visit `http://localhost:8080/` in your browser

## Features

✅ Cross-platform (Linux, Windows, macOS)  
✅ Serves HTML, CSS, JavaScript, images, JSON  
✅ Right-click integration (Linux)  
✅ Automatic browser opening  
✅ No configuration needed  

## Requirements

- Java 8 or higher
- Default web browser

## Author

Martin

---

**Enjoy naoMart!**
