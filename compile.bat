@echo off
if not exist "target\classes" mkdir target\classes
javac -d target/classes -sourcepath src/main/java src/main/java/edu/ccrm/cli/CCRMMain.java src/main/java/edu/ccrm/config/AppConfig.java src/main/java/edu/ccrm/domain/*.java src/main/java/edu/ccrm/io/*.java src/main/java/edu/ccrm/service/*.java src/main/java/edu/ccrm/util/*.java
if errorlevel 1 (
    echo Compilation failed!
    pause
    exit /b 1
)
echo Compilation successful!
call run.bat