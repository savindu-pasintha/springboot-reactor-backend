# ===== Stage 1: Build =====
FROM gradle:8.5-jdk21 AS build
WORKDIR /app

# Copy source files
COPY --chown=gradle:gradle . .

# Build app
RUN gradle build --no-daemon

# ===== Stage 2: Run =====
FROM eclipse-temurin:21.0.2_13-jdk-alpine
WORKDIR /app

# Copy jar from build stage
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8088
ENTRYPOINT ["java", "-jar", "app.jar"]