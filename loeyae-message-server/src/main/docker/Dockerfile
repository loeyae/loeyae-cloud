FROM openjdk:8-alpine
WORKDIR /app
ADD loeyae_message_server.jar message_server.jar
EXPOSE 9905
ENTRYPOINT ["java", "-Xmx512m", "-Xms512m", "-jar", "message_server.jar"]
#CMD exec java -Xmx512m -Xms512m -Djava.security.egd=file:/dev/./urandom $PARAM -jar /app/message_server.jar