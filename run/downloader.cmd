@echo off
set java_home=D:\deuce\app\jre1.8.0_111\bin\
set /p saveFilePath=<path.conf
echo [info] Save file download path : %saveFilePath%
cmd /k %java_home%/java.exe -jar Downloader.jar %saveFilePath%
