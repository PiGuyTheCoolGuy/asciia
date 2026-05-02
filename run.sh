#!/usr/bin/env bash

set -e

# ---- Config ----
SRC_DIR="src/main"
OUT_DIR="out"
MAIN_CLASS="app.Main"

# Build classpath (all jars in lib and javafx)
CLASSPATH=$(find lib -name "*.jar" | tr '\n' ':')

# JavaFX specific
JAVAFX_LIB="lib/javafx/lib"

# Clean output
echo "Cleaning output directory..."
rm -rf "$OUT_DIR"
mkdir -p "$OUT_DIR"

# Compile
echo "Compiling..."
javac \
  --module-path "$JAVAFX_LIB" \
  --add-modules javafx.controls,javafx.fxml \
  -cp "$CLASSPATH" \
  -d "$OUT_DIR" \
  $(find "$SRC_DIR" -name "*.java")

# Run
echo "Running..."
java \
  --module-path "$JAVAFX_LIB" \
  --add-modules javafx.controls,javafx.fxml \
  -cp "$OUT_DIR:$CLASSPATH" \
  "$MAIN_CLASS"