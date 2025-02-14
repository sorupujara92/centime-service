FROM openjdk:8-jdk-alpine

# Set the working directory
WORKDIR /opt

# Copy Maven wrapper and POM file
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Give execution permissions to Maven wrapper
RUN chmod +x mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy the source code
COPY src ./src

# Expose application port
EXPOSE 8080

# Build the application
RUN ./mvnw package

# Run the application
CMD ["java", "-jar", "target/*.jar"]
