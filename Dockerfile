# Stage 1: Building
FROM gradle:8.2.1-jdk17 AS builder

WORKDIR /app

COPY build.gradle settings.gradle /app/
COPY src /app/src

RUN gradle build --no-daemon

# Stage 2: Run
FROM openjdk:17-slim

WORKDIR /app

COPY --from=builder /app/build/libs/leiber-pizzeria-0.0.1.jar /app/leiber-pizzeria-app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "leiber-pizzeria-app.jar"]