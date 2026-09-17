#!/usr/bin/env bash

set -e

ROOT_DIR="$(cd "$(dirname "$0")" && pwd)"
OUT_DIR="$ROOT_DIR/out"
JAR_FILE="$ROOT_DIR/java-labs.jar"

rm -rf "$OUT_DIR" "$JAR_FILE"
mkdir -p "$OUT_DIR"

find "$ROOT_DIR/src" -name "*.java" > "$ROOT_DIR/sources.txt"

javac -encoding UTF-8 \
      -d "$OUT_DIR" \
      @"$ROOT_DIR/sources.txt"

rm "$ROOT_DIR/sources.txt"

jar cfm "$JAR_FILE" \
    "$ROOT_DIR/manifest.mf" \
    -C "$OUT_DIR" .

echo "Сборка завершена: $JAR_FILE"
echo "Запуск: java -jar $JAR_FILE"
