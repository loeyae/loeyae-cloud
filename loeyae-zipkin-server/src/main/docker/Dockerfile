FROM openjdk:8-alpine
WORKDIR /app
ADD loeyae_zipkin_server.jar zipkin_server.jar
EXPOSE 9902
ENTRYPOINT ["java", "-Xmx512m", "-Xms512m", "-jar", "zipkin_server.jar"]
#CMD exec java -Xmx512m -Xms512m -Djava.security.egd=file:/dev/./urandom $PARAM -jar /app/zipkin_server.jar