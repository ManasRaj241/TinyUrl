FROM openjdk:17

# Set working directory
WORKDIR /app

# Copy JAR file
COPY build/libs/url-generator-service.jar app.jar

# Expose the port the service runs on (adjust if needed)
EXPOSE 1110

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]