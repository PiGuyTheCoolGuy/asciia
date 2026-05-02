#!/bin/bash

echo "Compiling Java..."

GSON_JAR="lib/gson-2.9.1.jar"

mkdir -p out

javac -d out \
--module-path /usr/share/openjfx/lib \
--add-modules javafx.controls,javafx.fxml \
-cp "$GSON_JAR" \
$(find src -name "*.java")

echo "Copying assets..."

cp -r src/main/app out/

echo "Running..."

export DISPLAY=:0

java -cp "out:$GSON_JAR" \
--module-path /usr/share/openjfx/lib \
--add-modules javafx.controls,javafx.fxml \
app.Main
