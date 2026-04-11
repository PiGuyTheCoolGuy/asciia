#!/bin/bash

javac -d out --module-path /usr/share/openjfx/lib --add-modules javafx.controls,javafx.fxml $(find src -name "*.java")

java -cp out --module-path /usr/share/openjfx/lib --add-modules javafx.controls,javafx.fxml app.Main
