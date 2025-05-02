# Stage 1: Build with Gradle
FROM gradle:8.6-jdk21-jammy AS builder

WORKDIR /app

# Cache dependencies separately
COPY build.gradle.kts settings.gradle.kts ./
RUN gradle dependencies --no-daemon

# Build application
COPY src ./src
RUN gradle build --no-daemon -x test

# Extract layers (for faster startup)
RUN mkdir -p /layers && \
    java -Djarmode=layertools -jar /app/build/libs/*.jar extract --destination /layers

# Stage 2: Runtime
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Non-root user for security
RUN addgroup --system spring && \
    adduser --system --ingroup spring spring
USER spring:spring

# Copy layers from builder
COPY --from=builder --chown=spring:spring /layers/dependencies/ ./
COPY --from=builder --chown=spring:spring /layers/spring-boot-loader/ ./
COPY --from=builder --chown=spring:spring /layers/snapshot-dependencies/ ./
COPY --from=builder --chown=spring:spring /layers/application/ ./

# JVM optimization for containers
ENV JAVA_OPTS="\
  -XX:+UseContainerSupport \
  -XX:MaxRAMPercentage=75.0 \
  -XX:+HeapDumpOnOutOfMemoryError \
  -XX:HeapDumpPath=/tmp \
  -XX:+ExitOnOutOfMemoryError"

# Health check
HEALTHCHECK --interval=30s --timeout=3s \
  CMD curl -f http://localhost:8088/actuator/health || exit 1

EXPOSE 8088

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} org.springframework.boot.loader.JarLauncher"]