FROM adoptopenjdk:11-jre-hotspot
COPY /target/docker-app.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]