FROM maven:latest as maven
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:11-jre-slim

COPY --from=maven /home/app/target/*.war app.war
ENTRYPOINT ["java","-jar","/app.war"]
