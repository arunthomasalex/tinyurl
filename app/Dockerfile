FROM openjdk:8-jdk-alpine
LABEL maintainer="arunthomasalex@gmail.com"
VOLUME /main-app
ADD build/libs/app-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "/app.jar"]