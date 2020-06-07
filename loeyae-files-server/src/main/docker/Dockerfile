FROM openjdk:8-alpine
WORKDIR /app
ADD loeyae_files_server.jar files_server.jar
EXPOSE 9903
ENTRYPOINT ["java", "-Xmx512m", "-Xms512m", "-jar", "files_server.jar"]
#CMD exec java -Xmx512m -Xms512m -Djava.security.egd=file:/dev/./urandom $PARAM -jar /app/files_server.jar