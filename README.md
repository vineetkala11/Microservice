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
![](/img/flow-diagram.png)

## Installation



## Usage



## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
Vineet kala
