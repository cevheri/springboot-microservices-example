# Java Microservices - Hystrix Dashboard


## Dependencies
* Linux, VirtualBox or Docker Desktop
* Java 11
* Maven
* Git Config Server Repository  
* Spring Boot : 2.3.11.RELEASE
  * new version spring,  is not supported
* Hystrix Dashboard    
* Netflix Eureka Client
* Google Container Tools
* H2 Database
* Lombok
* Zipkin

## Configuration 
src/main/docker/app.yml
```yaml
version: '3.8'
services:
  hystrix-dashboard:
    image: cevheri/hystrix-dashboard
    environment:
      - _JAVA_OPTIONS=-Xmx512m -Xms256m
    ports:
      - 9295:9295
```

We will use github public repository for our configuration:
https://github.com/cevheri/microservices-config-server


---
## Development
```shell
$ ./mvnw package
$ java -jar target/*.jar
```
Visit : http://localhost:8761/eureka/apps/hystrix-dashboard

---
## Production With Docker
We will create Docker Image using Google Container Tools and run this Docker Image with Docker Compose.

### Build docker image:
```shell
$ ./mvnw -Pprod clean verify jib:dockerBuild

...
[INFO] Executing tasks:
[INFO] [==============================] 100.0% complete
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  17.728 s
[INFO] Finished at: 21:45:11+03:00
[INFO] ------------------------------------------------------------------------
```

---

### Run:
```shell
$ docker-compose -f src/main/docker/app.yml up -d

Creating hystrix-dashboard ... done
```
---
Visit : http://localhost:8761/eureka/apps/hystrix-dashboard
```xml
<application>
    <name>hystrix-dashboard</name>
    <instance>
        <instanceId>192.168.1.57:hystrix-dashboard:9295</instanceId>
        <hostName>service-registry</hostName>
        <app>hystrix-dashboard</app>
        <ipAddr>192.168.1.57</ipAddr>
        <status>UP</status>
        ...
        ...
    </instance>
</application>
```

---
### View docker images:
```shell
$ docker images

REPOSITORY                   TAG            IMAGE ID       CREATED             SIZE
cevheri/hystrix-dashboard       latest         67a100df8bcx   26 minutes ago      120MB
```

### View docker containers:
````shell
$ docker ps

CONTAINER ID   IMAGE                      COMMAND                  CREATED             STATUS          PORTS                                       NAMES
6ca74578a26x   cevheri/hystrix-dashboard      "bash -c /entrypoint…"   8 seconds ago       Up 6 seconds    0.0.0.0:9295->9295/tcp, :::9295->9295/tcp   hystrix-dashboard
````

### Stop Docker Compose:
```shell
$ docker-compose -f src/main/docker/app.yml down
```
