FROM openjdk:17

WORKDIR /app

COPY build/libs/url-resolver-service.jar app.jar

EXPOSE 1111

ENTRYPOINT ["java", "-jar", "app.jar"]