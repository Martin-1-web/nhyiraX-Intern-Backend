# naoMart - HTTP Server Launcher

A lightweight, cross-platform HTTP web server with GUI launcher. Right-click any HTML file and open it with naoMart!

## Requirements

**All Platforms:**
- Java 21 or higher (see installation below)
- Default web browser

**Download Java 21:**
- [Adoptium Temurin 21 (Recommended)](https://adoptium.net/temurin/releases/?version=21)
- [Oracle Java 21](https://www.oracle.com/java/technologies/downloads/#java21)

### Java Installation by Platform

**Linux**
```bash
# Ubuntu/Debian
sudo apt install openjdk-21-jre

# Fedora
sudo dnf install java-21-openjdk

# Or download from Adoptium and extract
tar -xzf OpenJDK21U-jre_x64_linux_hotspot_21.0.8_9.tar.gz
```

**Windows**
1. Download from [Adoptium](https://adoptium.net/temurin/releases/?version=21&os=windows&arch=x64)
2. Run the installer (.msi file)
3. Follow the installation wizard
4. Verify: Open Command Prompt and run `java -version`

**macOS (Intel)**
1. Download from [Adoptium](https://adoptium.net/temurin/releases/?version=21&os=mac&arch=x64)
2. Extract the .tar.gz file
3. Follow included instructions
4. Verify: Open Terminal and run `java -version`

**macOS (Apple Silicon M1/M2/M3)**
1. Download from [Adoptium](https://adoptium.net/temurin/releases/?version=21&os=mac&arch=aarch64)
2. Extract the .tar.gz file
3. Follow included instructions
4. Verify: Open Terminal and run `java -version`

## Installation

### Linux (Recommended)
1. Download the Linux bundle from releases
2. Extract the package
3. Run the installer:
   ```bash
   chmod +x install.sh
   ./install.sh
   ```
4. Done! You can now:
   - Right-click any HTML file and select "Open With" → "naoMart"
   - Or search for "naoMart" in your applications menu

### Windows & macOS
1. Make sure Java 21 is installed (see Requirements above)
2. Download `Server.jar` from releases
3. Run from command line:
   ```bash
   java -jar Server.jar /path/to/your/files
   ```
4. Your default browser opens at `http://localhost:8080/`

## Usage

### Method 1: Right-click HTML file (Linux only)
- Right-click any HTML file
- Select "Open With" → "naoMart"
- Server starts and default browser opens automatically

### Method 2: Command line (All platforms)
```bash
java -jar Server.jar ~/Desktop
```

Then visit `http://localhost:8080/` in your browser

### Specify different directories
```bash
# Serve from Documents
java -jar Server.jar ~/Documents

# Serve from Downloads
java -jar Server.jar ~/Downloads

# Serve from any custom directory
java -jar Server.jar /path/to/your/folder
```

## Features

✅ Cross-platform (Linux, Windows, macOS)
✅ Serves HTML, CSS, JavaScript, images, JSON
✅ Right-click integration (Linux)
✅ Automatic browser opening
✅ Ultra-fast startup (0.5 seconds)
✅ No configuration needed

## Troubleshooting

**"java: command not found" or "java is not recognized"**
- Java 21 is not installed or not in your PATH
- Install from [Adoptium](https://adoptium.net/temurin/releases/?version=21)
- Restart your terminal after installation
- Verify: Run `java -version`

**"Cannot find Server.jar"**
- Make sure you're in the directory where Server.jar is located
- Use the full path: `java -jar /full/path/to/Server.jar ~/Desktop`

**Browser doesn't open automatically**
- Manually visit `http://localhost:8080/` in your browser
- Check your firewall settings
- Try with a different browser

**Port 8080 is already in use**
- Another application is using port 8080
- Stop that application or restart your computer
- The server uses port 8080 by default (cannot be changed currently)

## Stopping naoMart

Press `Ctrl+C` in the terminal where the server is running.

## Author

Martin

---

**Enjoy naoMart!**
