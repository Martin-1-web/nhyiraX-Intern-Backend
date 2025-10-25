#!/bin/bash

# Get the directory of the right-clicked file
if [ -n "$1" ] && [ -f "$1" ]; then
    DIR=$(dirname "$(realpath "$1")")
else
    DIR=~/Desktop
fi

# Kill any existing servers
pkill -f "java -jar.*Server.jar" 2>/dev/null
sleep 1

# Start server from that directory
java -jar /home/martin/Downloads/Server.jar "$DIR" > /tmp/server.log 2>&1 &
sleep 5

# Open Firefox
DISPLAY=:0 /usr/bin/firefox http://localhost:8080/ &

# Show success message
zenity --info --text="Server started from:\n$DIR" --title="naoMart" 2>/dev/null
