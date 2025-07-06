# Use official Java 17 image
FROM eclipse-temurin:17-jdk

# Set working directory inside container
WORKDIR /app

# Copy everything to the container
COPY . .

# Give permission to mvnw
RUN chmod +x mvnw

# Build the Spring Boot application
RUN ./mvnw clean package -DskipTests

# Run the JAR
CMD ["java", "-jar", "target/CustomerCreateins-0.0.1-SNAPSHOT.jar"]
