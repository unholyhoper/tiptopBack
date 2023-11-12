# Use the OpenJDK 8 base image
FROM openjdk:8-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file of your application into the container
COPY target/users-micoservice-0.0.1-SNAPSHOT.jar app.jar

# Expose the port on which your Spring Boot application listens
EXPOSE 8080

# Command to run your Spring Boot application
CMD ["java", "-jar", "app.jar"]
