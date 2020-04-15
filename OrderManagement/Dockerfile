FROM openjdk:8
ADD target/OrderMyFood-0.0.1-SNAPSHOT.jar order-service.jar
EXPOSE 9082
ENTRYPOINT ["java","-jar","order-service.jar"]