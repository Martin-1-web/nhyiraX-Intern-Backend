#!/bin/bash

echo "Installing naoMart..."

# Copy to local directory
mkdir -p ~/.local/share/naomart
mkdir -p ~/.local/bin

# Copy Java runtime
cp -r java-runtime ~/.local/share/naomart/

# Copy JAR
cp Server.jar ~/.local/share/naomart/

# Create launcher that uses bundled Java
cat > ~/.local/bin/naomart-launcher << 'LAUNCHER'
#!/bin/bash
JAVA="$HOME/.local/share/naomart/java-runtime/bin/java"
DIR=$(dirname "$(realpath "$1")")
pkill -f "java -jar.*Server.jar" 2>/dev/null
sleep 1
"$JAVA" -jar ~/.local/share/naomart/Server.jar "$DIR" > /tmp/naomart.log 2>&1 &
sleep 0.5
if command -v xdg-open &> /dev/null; then
    DISPLAY=:0 xdg-open http://localhost:8080/ &
elif command -v open &> /dev/null; then
    open http://localhost:8080/ &
else
    DISPLAY=:0 /usr/bin/firefox http://localhost:8080/ &
fi
LAUNCHER

chmod +x ~/.local/bin/naomart-launcher

# Create desktop entry
mkdir -p ~/.local/share/applications
cat > ~/.local/share/applications/naomart-launcher.desktop << 'DESKTOP'
[Desktop Entry]
Version=1.0
Type=Application
Name=naoMart
Comment=naoMart - Lightweight HTTP Server
Icon=web-browser
Exec=bash -c '~/.local/bin/naomart-launcher "%f"'
Terminal=false
Categories=Utility;Development;
MimeType=text/html;text/plain;
DESKTOP

update-desktop-database ~/.local/share/applications/

echo "✓ Installation complete!"
echo "You can now right-click HTML files and open with 'naoMart'"
