#!/bin/bash

# ===== CONFIGURATION =====
IMAGE_NAME="your-app"
CONTAINER_NAME="spring-app"
PORT=8088
PROFILE="prod"
PLATFORM="linux/amd64"  # Change to "linux/arm64" for ARM environments
# ========================

# Build the Docker image
echo "Building Docker image ($IMAGE_NAME) for platform $PLATFORM..."
docker build --platform $PLATFORM -t $IMAGE_NAME .

# Check if build succeeded
if [ $? -ne 0 ]; then
  echo "Error: Docker build failed"
  exit 1
fi

# Stop and remove existing container if running
echo "Stopping existing container ($CONTAINER_NAME)..."
docker rm -f $CONTAINER_NAME 2>/dev/null

# Run the container
echo "Starting container with:"
echo "- Port: $PORT"
echo "- Profile: $PROFILE"
echo "- Platform: $PLATFORM"

docker run --platform $PLATFORM -d \
  -p $PORT:$PORT \
  -e "SPRING_PROFILES_ACTIVE=$PROFILE" \
  --name $CONTAINER_NAME \
  $IMAGE_NAME

# Verify container started
if [ $? -eq 0 ]; then
  echo "Success! Container $CONTAINER_NAME is running."
  echo "Application should be available at: http://localhost:$PORT/"
else
  echo "Error: Failed to start container"
  exit 1
fi