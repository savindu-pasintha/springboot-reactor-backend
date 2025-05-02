@echo off
SETLOCAL

:: ===== CONFIGURATION =====
SET IMAGE_NAME=spring-app-image
SET CONTAINER_NAME=spring-app-container
SET PORT=8088
SET PROFILE=prod
SET PLATFORM=linux/amd64
:: ========================

:: Build the Docker image
echo Building Docker image (%IMAGE_NAME%) for platform %PLATFORM%...
docker build --platform %PLATFORM% -t %IMAGE_NAME% .

IF %ERRORLEVEL% NEQ 0 (
  echo Error: Docker build failed
  EXIT /B 1
)

:: Stop and remove existing container
echo Stopping existing container (%CONTAINER_NAME%)...
docker rm -f %CONTAINER_NAME% 2>nul

:: Run the container
echo Starting container with:
echo - Port: %PORT%
echo - Profile: %PROFILE%
echo - Platform: %PLATFORM%

docker run --platform %PLATFORM% -d ^
  -p %PORT%:%PORT% ^
  -e "SPRING_PROFILES_ACTIVE=%PROFILE%" ^
  --name %CONTAINER_NAME% ^
  %IMAGE_NAME%

IF %ERRORLEVEL% EQU 0 (
  echo Success! Container %CONTAINER_NAME% is running.
  echo Application should be available at: http://localhost:%PORT%/
) ELSE (
  echo Error: Failed to start container
  EXIT /B 1
)

ENDLOCAL