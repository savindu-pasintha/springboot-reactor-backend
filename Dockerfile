# Use Java 21.0.2 JDK base image
FROM eclipse-temurin:21.0.2_13-jdk-alpine

# Set working directory
WORKDIR /app

# Copy JAR file
COPY build/libs/*.jar app.jar

# Expose port
EXPOSE 8088

# Run application
ENTRYPOINT ["java", "-jar", "app.jar"]
