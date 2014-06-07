@echo off
title Universal Android Toolkit
color 0a
goto :A

:A
:: Start the process, wait for it to end
echo Starting Universal Android Toolkit...
.\jre\win32_x86\bin\java.exe -jar .\bin\Universal_Android_Toolkit.jar
echo Universal Android Toolkit has exited...
pause