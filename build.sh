#!/usr/bin/env bash
set -e

rm -rf out lab2.jar
mkdir -p out

find src -name "*.java" > sources.txt
javac -d out @sources.txt
jar cfm lab2.jar manifest.mf -C out .
rm sources.txt

echo "Сборка завершена: lab2.jar"
