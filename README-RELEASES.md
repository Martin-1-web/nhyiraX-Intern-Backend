# naoMart - Release Information

## Current Releases

### v2.0 - Linux Bundle (Recommended for Linux Users)
- **Includes:** Java 21 JRE bundled
- **Size:** ~50MB
- **Installation:** `chmod +x install.sh && ./install.sh`
- **No dependencies needed**

### v1.0 - Source Code + JAR (All Platforms)
- **Includes:** Source code and Server.jar
- **Requires:** Java 21 installed on your system
- **Size:** ~5MB
- **Best for:** Users who already have Java installed

## Supported Platforms

| Platform | v2.0 | v1.0 |
|----------|------|------|
| Linux    | ✅   | ✅   |
| Windows  | ⏳   | ✅   |
| macOS    | ⏳   | ✅   |

*⏳ = Coming in future releases*

## Installation

### Linux (v2.0 - Recommended)
```bash
chmod +x install.sh
./install.sh
# Then right-click any HTML file and select "Open With" → "naoMart"
```

### Windows (v1.0)
**Step 1: Install Java 21**

Download and install Java 21 from one of these sources:
- [Adoptium Temurin 21 (Recommended)](https://adoptium.net/temurin/releases/?version=21)
- [Oracle Java 21](https://www.oracle.com/java/technologies/downloads/#java21)

**Step 2: Run naoMart**
```cmd
java -jar Server.jar C:\Users\YourName\Desktop
```

Visit `http://localhost:8080/` in your browser.

### macOS (v1.0)

**Step 1: Install Java 21**

Download and install Java 21 for your Mac:
- [Adoptium Temurin 21 - Intel Mac](https://adoptium.net/temurin/releases/?version=21&os=mac&arch=x64)
- [Adoptium Temurin 21 - Apple Silicon (M1/M2/M3)](https://adoptium.net/temurin/releases/?version=21&os=mac&arch=aarch64)

**Step 2: Run naoMart**
```bash
java -jar Server.jar ~/Desktop
```

Visit `http://localhost:8080/` in your browser.

### All Platforms (Command Line)

```bash
java -jar Server.jar /path/to/your/files
```

Then visit `http://localhost:8080/` in your default browser.

## Java 21 Installation Guide

### Windows
1. Download from [Adoptium](https://adoptium.net/temurin/releases/?version=21)
2. Run the installer (.msi)
3. Follow the installation wizard
4. Verify: Open Command Prompt and run `java -version`

### macOS
1. Download from [Adoptium](https://adoptium.net/temurin/releases/?version=21)
2. Choose your Mac type (Intel or Apple Silicon)
3. Extract the .tar.gz file
4. Follow the included installation instructions
5. Verify: Open Terminal and run `java -version`

### Linux
1. Download from [Adoptium](https://adoptium.net/temurin/releases/?version=21)
2. Extract: `tar -xzf OpenJDK*.tar.gz`
3. Verify: `java -version`

Or use your package manager:
```bash
sudo apt install openjdk-21-jre    # Ubuntu/Debian
sudo dnf install java-21-openjdk   # Fedora
```

## Features

✅ Cross-platform (Linux, Windows, macOS)
✅ Serves HTML, CSS, JavaScript, images, JSON
✅ Right-click integration (Linux)
✅ Automatic browser opening
✅ Ultra-fast startup (0.5 seconds)
✅ No configuration needed

## Troubleshooting

**"java: command not found"**
- Install Java 21 from [Adoptium](https://adoptium.net/temurin/releases/?version=21)
- Verify installation: `java -version`

**"Cannot find Server.jar"**
- Make sure you're in the directory where Server.jar is located
- Use the full path: `java -jar /path/to/Server.jar ~/Desktop`

**Browser doesn't open automatically**
- Manually visit `http://localhost:8080/` in your browser
- Check your firewall settings

## Future Releases

- v2.0-Windows - Bundled Windows installer with Java 21
- v2.0-macOS - Bundled macOS installers with Java 21

## Author

Martin

---

**Enjoy naoMart!**
