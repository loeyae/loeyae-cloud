FROM openjdk:8-alpine
WORKDIR /app
ADD loeyae_service_center.jar service_center.jar
EXPOSE 9900
ENTRYPOINT ["java", "-Xmx512m", "-Xms512m", "-jar", "service_center.jar"]
#CMD exec java -Xmx512m -Xms512m -Djava.security.egd=file:/dev/./urandom $PARAM -jar /app/service_center.jar