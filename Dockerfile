FROM adoptopenjdk/openjdk11:latest
VOLUME /tmp
RUN /bin/mvn clean install
ADD target/*.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
