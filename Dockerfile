FROM maven:latest as maven
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:11-jre-slim
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
COPY --from=build /home/app/target/*.war app.war
ENTRYPOINT ["java","-jar","/app.war"]
