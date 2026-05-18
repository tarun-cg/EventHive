# Stage 1: Build stage
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy only pom.xml first to leverage Docker cache for dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Runtime stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Create a non-root user for better security
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copy the JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
