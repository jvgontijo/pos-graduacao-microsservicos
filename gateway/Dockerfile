FROM maven:3.6.3-openjdk-17-slim as build
COPY /src /app/src
COPY /pom.xml /app
RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
