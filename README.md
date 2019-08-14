# Creating Microservices With Spring and Eureka Server

This project based on Spring boot Microservice and covering all the aspects of Microservice Architecture end to end. Below are the key topics covered in this project-

- Spring Boot
- Eureka Server
- Zuul (Api Gateway)
- Ribbon (Load Balancer)
- Feign (Rest Client)
- Hystrix (Circuit Breaker)
- Docker
- Docker Compose
- Kubernetes Deployment
- Ingress-Nginx

## Prerequisite

- Java 8
- Docker (I was using docker for desktop windows version=18.09.2)
- Kubernetes (I was using built in "docker for desktop" kubernetes)
- Clone this repository and run "mvn clean install" inside all four projects (CatalogService, EurekaServer, Gateway, CatalogData)

## Project Explanation

I found many examples over Internet which covering topics like "Springboot Microservice Architecture" or "Deploy Microservices on Docker Containers" or "Manage Docker Images with Kubertenes clusters". But didn't found which covering all topics in single example step by step.
In this project I have created 3 Eureka Clients (CatalogService, Gateway, CatalogData) and one Eureka Server (EurekaServer).

EurekaServer: This microservice is acting as Eureka Server, all other service register themselves in EurekaServer. 
Gateway: This microservice acting as a proxy server.
CatalogData: This microservice acting as Database layer.
CatalogService: This microservice is a middle layer between Gateway and CatalogData.

*Note: In this project I was not maintaining any business login, propose of this project to build our understanding towards Microservice Architecture and DevOps parts in Microservice world. That why we are not making any database interaction in "CatalogData" service and maintaining in memory list.

> Flow Digram
![Service Flow diagram](/img/flow-diagram.png)

## Services Description

Here are trying to implement Microservice Architecture using these four services. Quick look in role of each service in this Architecture and implementation of Spring module in each service.

- EurekaServer : Eureka Server  (refer to code to check implementation)
- Gateway: Eureka Client, Implemented Zuul Proxy Gateway (refer to code to check implementation)
- CatalogService: Eureka Client, Feign (Rest Client), Ribbon (Load Balancer), Hystrix (Circuit Breaker)  (refer to code to check implementation)
- CatalogData: Eureka Client (refer to code to check implementation)

## Installation



## Run project as a Spring boot application:

- Import all four project as a maven project in your IDE (I am using eclipse as IDE).
- Right click EurekaServer project and run as a Spring boot Application.
	- Open browser and try to access http://localhost:8761/ .If everything setup correctly you are able to view Eureka server home page.
	
![Eureka Server](/img/spring-eureka-server.png)
- Our Eureka server is running and ready to register Eureka Clients.
- Run other three Services (CatalogService, Gateway, CatalogData) as Spring Boot application, once all three clients up and running, again try to access http://localhost:8761/ ,you will find all three services is registered in Eureka client.

![Eureka Server Clients](/img/spring-eureka-server-all.png)
- At this moment all four service is up and running. Before hitting our API, I would like to share PORT details for each service as describe in application.properties file.
	- EurekaServer: 8761
	- Gateway: 8084
	- CatalogService: 8100
	- CatalogData: 8000
- Its time to check our Gateway API response, open browser and try to access Gateway proxy url http://localhost:8084/catservice/service/fetchCatalog .You must be seeing below response in your screen.

`[{"itemName":"Cricket Bat","itemCount":100,"port":"8000"},{"itemName":"Cricket Ball","itemCount":350,"port":"8000"},{"itemName":"Football","itemCount":30,"port":"8000"}]`

- Here we redirected our Gateway Proxy Url towards CatalogService endpoint, to verify Gateway proxy configuration please refer to application.properties file line no 5. Here on the basis of "catservice" keyword we are redirecting our request towards  CatalogService (here "catalog-service" is the name of service, mentioned in its application.properties file).

`zuul.routes.catservice.serviceId=${CATALOG_SERVICE:catalog-service}`

## Run project using Docker-Compose:

For this segment we need docker installed in our machine. 

- Refer to Dockerfile in each service. Below is the sample Dockerfile to create on image of Service. 


    FROM openjdk:8
    WORKDIR /usr/app
    ADD target/eureka-server.jar eureka-server.jar
    EXPOSE 8761
    ENTRYPOINT ["java","-jar","eureka-server.jar"]

- Run below command in each Service directory to create an Docker Image 


    cd EurekaServer
    docker build -t eureka-server .
    cd Gateway
    docker build -t gateway .
    cd CatalogData
    docker build -t catalog-data .
    cd CatalogService
    docker build -t catalog-service .
	
- Once done with Docker image creation, run below command to verify Docker image. You must see all images with the given Tag name as given in below snapshot.

	`docker images`

![Docker Images](/img/docker-image.png)

## Run project in a  Kubernetes Cluster:

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
Vineet kala
