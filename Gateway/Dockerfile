FROM openjdk:8
CMD ["cd","../Gateway"]
ADD target/Gateway-0.0.1-SNAPSHOT.jar Gateway-0.0.1-SNAPSHOT.jar
EXPOSE 8084
ENTRYPOINT ["java","-jar","Gateway-0.0.1-SNAPSHOT.jar"]