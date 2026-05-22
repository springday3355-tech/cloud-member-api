FROM amazoncorretto:17-al2023-headless
WORKDIR /app
COPY build/libs/*-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]