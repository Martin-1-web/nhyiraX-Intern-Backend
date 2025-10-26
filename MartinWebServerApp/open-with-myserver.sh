#!/bin/bash
DIR="$(cd "$(dirname "$0")" && pwd)"
"$DIR/customjre/bin/java" -jar "$DIR/ServerDemo.jar" "$1"
