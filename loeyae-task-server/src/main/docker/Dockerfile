FROM openjdk:8-alpine
WORKDIR /app
ADD loeyae_task_server.jar task_server.jar
EXPOSE 9905
ENTRYPOINT ["java", "-Xmx512m", "-Xms512m", "-jar", "task_server.jar"]
#CMD exec java -Xmx512m -Xms512m -Djava.security.egd=file:/dev/./urandom $PARAM -jar /app/task_server.jar