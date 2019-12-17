FROM openjdk:8-jdk-alpine
VOLUME /tmp
WORKDIR /app
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]