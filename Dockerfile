# Stage 1: build
FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /app
COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts settings.gradle.kts .
COPY src src

RUN ./gradlew bootJar --no-daemon

# Stage 2: run
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
RUN adduser -D appuser && chown -R appuser /app
USER appuser
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
