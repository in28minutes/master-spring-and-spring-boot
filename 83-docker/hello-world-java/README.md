# Dockerfile Examples

## Docker commands
- docker build -t in28min/hello-world-docker:v1 .


## Dockerfile - 1 - Creating Docker Images

```
FROM openjdk:25-jdk-slim
COPY target/*.jar app.jar
EXPOSE 5000
ENTRYPOINT ["java","-jar","/app.jar"]
```

## Dockerfile - 2 - Build Jar File - Multi Stage
```
FROM maven:3.9.11-amazoncorretto-25-alpine AS build
WORKDIR /home-world-java
COPY . /home-world-java
RUN mvn clean package -DskipTests

FROM openjdk:25-jdk-slim
EXPOSE 5000
COPY --from=build /home-world-java/target/*.jar app.jar
ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]

```

## Dockerfile - 3 - Caching

```

[//]: # (FROM maven:3.9.11-amazoncorretto-25-alpine AS build)

[//]: # (WORKDIR /home-world-java)

[//]: # ()
[//]: # (COPY ./pom.xml /home-world-java/pom.xml)

[//]: # (COPY ./src/main/java/com/in28minutes/rest/webservices/restfulwebservices/RestfulWebServicesApplication.java	/home/app/src/main/java/com/in28minutes/rest/webservices/restfulwebservices/RestfulWebServicesApplication.java)

[//]: # ()
[//]: # (RUN mvn -f /home/app/pom.xml clean package)

[//]: # ()
[//]: # (COPY . /home/app)

[//]: # (RUN mvn -f /home/app/pom.xml clean package)

[//]: # ()
[//]: # (FROM openjdk:25-jdk-slim)

[//]: # (EXPOSE 5000)

[//]: # (COPY --from=build /home/app/target/*.jar app.jar)

[//]: # (ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ])

# ====== BUILD STAGE ======
FROM maven:3.9.11-amazoncorretto-25-alpine AS build

# Set working directory
WORKDIR /home-world-java

# Copy only pom.xml first for dependency caching
COPY pom.xml .

# Download dependencies (cached unless pom.xml changes)
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# ====== RUNTIME STAGE ======
FROM openjdk:25-jdk-slim

WORKDIR /home-world-java

# Expose port
EXPOSE 5000

# Copy the jar from build stage
COPY --from=build /home-world-java/target/*.jar app.jar

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```
