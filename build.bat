@echo off
rmdir /s /q out 2>nul
del lab2.jar 2>nul
mkdir out

dir /s /b src\*.java > sources.txt
javac -d out @sources.txt
jar cfm lab2.jar manifest.mf -C out .
del sources.txt

echo Сборка завершена: lab2.jar
