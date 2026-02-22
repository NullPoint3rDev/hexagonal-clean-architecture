@rem Gradle startup script for Windows
@if "%DEBUG%"=="" @echo off
set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.
set APP_HOME=%DIRNAME%
set CLASSPATH=%APP_HOME%\gradle\wrapper\gradle-wrapper.jar
if defined JAVA_HOME set JAVA_EXE=%JAVA_HOME%\bin\java.exe
if not defined JAVA_EXE set JAVA_EXE=java.exe
"%JAVA_EXE%" "-Dorg.gradle.appname=%~n0" -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*
