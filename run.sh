#!/usr/bin/env bash

# === CONFIG ===
SRC_DIR="src"
OUT_DIR="out"
MAIN_CLASS="app.Main"

# === BUILD CLASSPATH ===
# Collect all jars from javafx/lib and lib
CP=$(find javafx/lib lib -name "*.jar" | tr '\n' ':')

# Create output directory
mkdir -p "$OUT_DIR"

echo "Compiling..."

javac \
  --class-path "$CP" \
  -d "$OUT_DIR" \
  $(find "$SRC_DIR" -name "*.java")

if [ $? -ne 0 ]; then
  echo "Compilation failed"
  exit 1
fi

echo "Running..."

java \
  --class-path "$OUT_DIR:$CP" \
  "$MAIN_CLASS"