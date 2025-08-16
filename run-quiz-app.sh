#!/bin/bash

echo "Starting Online Quiz Application..."
echo

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "ERROR: Java is not installed or not in PATH"
    echo "Please install Java 21 or higher and try again"
    exit 1
fi

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "ERROR: Maven is not installed or not in PATH"
    echo "Please install Maven and try again"
    exit 1
fi

echo "Building the application..."
mvn clean package -DskipTests

if [ $? -ne 0 ]; then
    echo "ERROR: Build failed"
    exit 1
fi

echo
echo "Starting the Quiz Application..."
echo "Open your browser and go to: http://localhost:8080"
echo
echo "Admin Login: admin@quizapp.com / admin123"
echo
echo "Press Ctrl+C to stop the application"
echo

java -jar target/online-quiz-app-0.0.1-SNAPSHOT.jar