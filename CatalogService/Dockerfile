FROM openjdk:8
WORKDIR /usr/app
ADD target/Catalog-Service.jar Catalog-Service.jar
EXPOSE 8100
ENTRYPOINT ["java","-jar","Catalog-Service.jar"]