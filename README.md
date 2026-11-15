# Лабораторная работа № 8 Принципы SOLID

## ФИО: Луцык Андрей Сергеевич
## Группа: ФИТ-251

## сборка
### Remove-Item out -Recurse -Force -ErrorAction SilentlyContinue
### New-Item -ItemType Directory -Path out
### $files = Get-ChildItem src -Recurse -Filter *.java | ForEach-Object { $_.FullName }
### javac -d out $files

## Запуск
### java -cp out Main