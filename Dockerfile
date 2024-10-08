# Dockerfile
FROM eclipse-temurin:17-alpine
COPY target/app.jar /app.jar
EXPOSE 7777
ENTRYPOINT ["java", "-jar", "/app.jar"]
