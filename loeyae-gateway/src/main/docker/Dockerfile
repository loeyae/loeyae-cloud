FROM openjdk:8-alpine
WORKDIR /app
ADD loeyae_gateway.jar gateway.jar
EXPOSE 9901
ENTRYPOINT ["java", "-Xmx512m", "-Xms512m", "-jar", "gateway.jar"]
#CMD exec java -Xmx512m -Xms512m -Djava.security.egd=file:/dev/./urandom $PARAM -jar /app/gateway.jar