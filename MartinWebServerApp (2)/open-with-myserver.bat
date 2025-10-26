@echo off
set DIR=%~dp0
"%DIR%customjre\bin\java.exe" -jar "%DIR%ServerDemo.jar" %1
