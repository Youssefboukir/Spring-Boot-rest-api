FROM openjdk:17

EXPOSE 8080

ADD target/rest-api.jar rest-api.jar

ENTRYPOINT ["java","-jar","/rest-api.jar"]
