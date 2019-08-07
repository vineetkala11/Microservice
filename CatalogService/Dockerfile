FROM openjdk:8
WORKDIR /usr/app 
ADD target/Catalog-Data.jar Catalog-Data.jar
EXPOSE 8100
ENTRYPOINT ["java","-jar","Catalog-Data.jar"]