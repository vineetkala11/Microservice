# Creating Microservices With Spring, Eureka Server, Redis Cache, Docker and Kubernetes

This project based on Spring boot Microservice and covering all the aspects of Microservice Architecture end to end. Below are the key topics covered in this project-

- Spring Boot
- Eureka Server
- Zuul (Api Gateway)
- Ribbon (Load Balancer)
- Feign (Rest Client)
- Hystrix (Circuit Breaker)
- Redis (Caching Server)
- Docker
- Docker Compose
- Kubernetes Deployment
- Ingress-Nginx

## Prerequisite

- Java 8
- Docker (I was using docker for desktop windows version=18.09.2)
- Kubernetes (I was using built in "docker for desktop" kubernetes)
- Install kubectl
- Clone this repository and run "mvn clean install" inside all four projects (CatalogService, EurekaServer, Gateway, CatalogData)

## Project Explanation

I found many examples over Internet which covering topics like "Springboot Microservice Architecture" or "Deploy Microservices on Docker Containers" or "Manage Docker Images with Kubertenes clusters". But didn't found which covering all topics in single example step by step.
In this project I have created 3 Eureka Clients (CatalogService, Gateway, CatalogData) and one Eureka Server (EurekaServer).

- EurekaServer: This microservice is acting as Eureka Server, all other service register themselves in EurekaServer. 
- Gateway: This microservice acting as a proxy server.
- CatalogData: This microservice acting as Database layer.
- CatalogService: This microservice is a middle layer between Gateway and CatalogData.

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

Download Docker hub for windows or Mac from below link

- https://docs.docker.com/docker-for-mac/install/
- https://docs.docker.com/docker-for-windows/install/


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

```
[{"itemName":"Cricket Bat","itemCount":100,"port":"8000"},{"itemName":"Cricket Ball","itemCount":350,"port":"8000"},{"itemName":"Football","itemCount":30,"port":"8000"}]
```

- Here we redirected our Gateway Proxy Url towards CatalogService endpoint, to verify Gateway proxy configuration please refer to application.properties file line no 5. Here on the basis of "catservice" keyword we are redirecting our request towards  CatalogService (here "catalog-service" is the name of service, mentioned in its application.properties file).

`zuul.routes.catservice.serviceId=${CATALOG_SERVICE:catalog-service}`

## Run project using Docker-Compose:

For this segment we need docker installed in our machine. 

- Refer to Dockerfile in each service. Below is the sample Dockerfile to create an image of Service. 

```
	FROM openjdk:8
	WORKDIR /usr/app
	ADD target/eureka-server.jar eureka-server.jar
	EXPOSE 8761
	ENTRYPOINT ["java","-jar","eureka-server.jar"]
```
- Run below command in each Service directory to create Docker Image 

```
	cd EurekaServer
 	docker build -t eureka-server .
	cd Gateway
	docker build -t gateway .
	cd CatalogData
	docker build -t catalog-data .
	cd CatalogService
	docker build -t catalog-service .
```

![Docker build](/img/docker-build.png)
	
- Once done with Docker image creation, run below command to verify Docker images. You must see all images with the given Repository name, as shown in below snapshot.

	`docker images`

![Docker Images](/img/docker-image.png)

- We are done with Docker Image creation for all 4 servies.
- Now its time to run all 4 images in Docker container using Docker-Compose.
- Switch the directory to "docker-compose" in command prompt.
- Run below command to execute docker-compose.yml file

	`docker-compose up`

- All 4 services started running and logs get printed in console.
- Once all service start running, to verify status of the sevices, open browser and try to access http://localhost:8761/ .Spring Eureka page get open with all 3 registered client.

- You can also scale services by running multiple container of single image, below docker-compose command scale up "catalog-data" by 3 and "catalog-service" by 2 containers. You can easly identify multiple instanse by accessing url http://localhost:8761/ .Docker itself manage port assignment for each container.

	`docker-compose up --scale catalog-data=3 --scale catalog-service=2`

## Run project in a Kubernetes Cluster:
Till now we are good enough to create Docker image of our service and running images as a container using docker-compose. We  also done with the scalling part  of our containers.

But this is not the case always, in production environment you might need a better managment for your containers. Here Kuberenetes comes in picture.

Kubernetes is a cluster and container management tool. It lets you deploy containers to clusters, meaning a network of virtual machines. It works with different containers, not just Docker. The basic idea of Kubernetes is to further abstract machines, storage, and networks away from their physical implementation.

- In this project I have used Kubertenes version comes with the installation of "Docker-for-Desktop".

![Docker Images](/img/kubernetes-docker-for-desktop.png)

- Minikube is the another way to install Kubernetes in your machine, I was facing space issue with Minikube because Minikube expects VirtualBox to be used. For windows 10 users, using HyperV Manager you can create VirtualBox and Virtual Switch.
- Next you need to install kubectl, below are the steps to follow for windows -
	- Create a new directory that you will move your kubectl binaries into. A good place would be C:\bin
	- Download the latest kubectl executable from the link on the Kubernetes doc page.
	`https://kubernetes.io/docs/tasks/tools/install-kubectl/#install-kubectl-on-windows`
	- Move this downloaded .exe file into the bin directory you created.
	- Use Windows search to type “env” then select “Edit the system environment variables”
	- In the System Properties dialog box, click “Environment Variables”.
	- In System Variables click on the “Path” Variable and then click “Edit”
	- Click “New” and then type C:\bin
	- Drag the newly created path so that it is higher in order than Docker's binaries. This is very important and will ensure that you will not have an out of date kubectl client.
	- Click "OK"
	- Restart your terminal and test by typing kubectl into it. You should get the basic commands and help menu printed back to your screen. If this doesn't work try restarting your machine.
	- Run kubectl version to verify that you are using the newest version and not the out of date v1.10 version.
- So we are done with Kubernetes setup part, lets deploy our services in Kubernetes cluster.
- Apart from docker-compose, kubernetes didn't manage Docker images available  in local machine. To make our Docker images available for Kubertenes cluster, we need to push our images in Docker Hub.
- To push our local images to Docker Hub, we need to run following commands-
	- `docker login` (it will ask our DockerHub user and password)
	- `docker push "our image name"`
	
	**Note : If you do not have DockerHub login, use images from my Docker Hub repository.*
	 
	![Docker Images](/img/my-docker-hub.png)
	

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
Vineet kala
