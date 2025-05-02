#!/bin/bash

echo "Building Docker image..."
docker build -t spring-app-image .

echo "Running Docker container..."
docker run -d -p 8088:8088 --name spring-app-container spring-app-image

echo "Application is now running on http://localhost:8088"
