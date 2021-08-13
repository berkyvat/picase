FROM maven:latest as maven
COPY ./pom.xml ./pom.xml
COPY ./src ./src
RUN mvn dependency:go-offline -B
RUN mvn package
FROM openjdk:11-jre-slim
WORKDIR /adevguide
COPY --from=maven target/SimpleJavaProject-*.jar ./SimpleJavaProject.jar
CMD ["java", "-jar", "./SimpleJavaProject.jar"]
