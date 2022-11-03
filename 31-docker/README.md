# Dockerfile Examples

## Docker commands
- docker build -t in28min/hello-world-docker:v1 .


## Dockerfile - 1 - Creating Docker Images

```
FROM openjdk:18.0-slim
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
```

## Dockerfile - 2 - Build Jar File - Multi Stage
```
FROM maven:3.8.6-openjdk-18-slim AS build
WORKDIR /home/app
COPY . /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:18.0-slim
VOLUME /tmp
EXPOSE 8080
COPY --from=build /home/app/target/*.jar app.jar
ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]
```

## Dockerfile - 3 - Caching
```
FROM maven:3.8.6-openjdk-18-slim AS build
WORKDIR /home/app

COPY ./pom.xml /home/app/pom.xml
COPY ./src/main/java/com/example/demodocker/DemoDockerApplication.java /
	/home/app/src/main/java/com/example/demodocker/DemoDockerApplication.java

RUN mvn -f /home/app/pom.xml clean package

COPY . /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:18.0-slim
VOLUME /tmp
EXPOSE 8080
COPY --from=build /home/app/target/*.jar app.jar
ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]
```
