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

`$ [{"itemName":"Cricket Bat","itemCount":100,"port":"8000"},{"itemName":"Cricket Ball","itemCount":350,"port":"8000"},{"itemName":"Football","itemCount":30,"port":"8000"}]`

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
Vineet kala
