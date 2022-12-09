FROM openjdk:17-alpine

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} profile-service.jar

ENTRYPOINT ["java", "-jar", "profile-service.jar"]