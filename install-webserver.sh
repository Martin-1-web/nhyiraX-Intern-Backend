#!/bin/bash

echo "Installing naoMart - HTTP Server Launcher..."

# Create scripts directory
mkdir -p ~/.local/bin

# Copy launcher script
cp ~/WebServer-GUI.sh ~/.local/bin/naomart-launcher
chmod +x ~/.local/bin/naomart-launcher

# Copy JAR file
mkdir -p ~/.local/share/naomart
cp ~/Downloads/Server.jar ~/.local/share/naomart/

# Create desktop entry
mkdir -p ~/.local/share/applications
cat > ~/.local/share/applications/naomart-launcher.desktop << 'EOF'
[Desktop Entry]
Version=1.0
Type=Application
Name=naoMart
Comment=naoMart - Lightweight HTTP Server for serving HTML files
Icon=firefox
Exec=~/.local/bin/naomart-launcher %f
Terminal=false
Categories=Utility;Development;
MimeType=text/html;
EOF

# Update database
update-desktop-database ~/.local/share/applications/

echo "✓ Installation complete!"
echo "Now you can right-click HTML files and open with 'naoMart'"
