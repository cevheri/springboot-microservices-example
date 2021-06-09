# Sprint Boot Microservices Example



 
---
### Services Registry
- Spring Boot version: 2.5.0
- Dependencies:
  - spring-cloud-starter-netflix-eureka-server
---
### API Gateway
Spring Boot version: 2.3.11.RELEASE
- Dependencies:
  - spring-cloud-starter-gateway  
  - spring-cloud-starter-netflix-hystrix
  - spring-cloud-starter-netflix-eureka-client
  - spring-cloud-starter-config
  - 
---
### Config Server
- Spring Boot version: 2.5.0
- Github repository -> application.yml 
- Dependencies:
  - spring-cloud-config-server
  - spring-cloud-starter-netflix-eureka-client
- Zookeper (not yet)
---
### Hystrix Dashboard
- Spring Boot version: 2.3.11.RELEASE
- Dependencies:
  - spring-cloud-starter-config
  - spring-cloud-starter-netflix-hystrix-dashboard
  - spring-cloud-starter-netflix-eureka-client
---
### Other Microservices
- Spring Boot version: 2.5.0
- User, Department, Task Services
- Dependencies:
  - spring-cloud-starter-config
  - spring-cloud-starter-netflix-eureka-client
  - spring-cloud-starter-bootstrap
  - com.h2database.h2
  - org.projectlombok.lombok
  - spring-boot-starter-data-jpa
  - spring-boot-starter-web 
---


### Zipkin
Docker
The Docker Zipkin project is able to build docker images, provide scripts and a docker-compose.yml for launching pre-built images. The quickest start is to run the latest image directly:

docker run -d -p 9411:9411 openzipkin/zipkin
 
---

Health : http://localhost:9191/actuator/health
Eureka : http://localhost:8761/
Department GetMethod: http://localhost:9191/departments/1
Hystrix: http://localhost:9295/hystrix
http://localhost:9295/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A9191%2Factuator%2Fhystrix.stream



blockchain
