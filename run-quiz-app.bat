@echo off
echo Starting Online Quiz Application...
echo.

REM Check if Java is installed
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ERROR: Java is not installed or not in PATH
    echo Please install Java 21 or higher and try again
    pause
    exit /b 1
)

REM Check if Maven is installed
mvn -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ERROR: Maven is not installed or not in PATH
    echo Please install Maven and try again
    pause
    exit /b 1
)

echo Building the application...
call mvn clean package -DskipTests

if %errorlevel% neq 0 (
    echo ERROR: Build failed
    pause
    exit /b 1
)

echo.
echo Starting the Quiz Application...
echo Open your browser and go to: http://localhost:8080
echo.
echo Admin Login: admin@quizapp.com / admin123
echo.
echo Press Ctrl+C to stop the application
echo.

java -jar target/online-quiz-app-0.0.1-SNAPSHOT.jar

pause