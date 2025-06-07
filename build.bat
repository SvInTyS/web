@echo off
echo Очистка старых .class файлов...
rmdir /s /q build
mkdir build

rmdir /s /q web\WEB-INF\classes
mkdir web\WEB-INF\classes

echo Компиляция .java файлов...
javac -encoding UTF-8 -d build -classpath "lib\sqlite-jdbc-3.49.1.0.jar;lib\javax.servlet-api-4.0.1.jar" src\auth\*.java src\catalog\*.java

echo Копирование в WEB-INF\classes...
xcopy /s /y build\* web\WEB-INF\classes\

echo ✅ Сборка завершена!
