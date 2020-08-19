FROM openjdk:8-alpine
WORKDIR /app
ADD loeyae_demo.jar demo.jar
EXPOSE 9905
ENTRYPOINT ["java", "-Xmx512m", "-Xms512m", "-jar", "demo.jar"]
#CMD exec java -Xmx512m -Xms512m -Djava.security.egd=file:/dev/./urandom $PARAM -jar /app/demo.jar