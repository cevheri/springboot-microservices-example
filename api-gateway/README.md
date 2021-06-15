# Java Microservices - API Gateway


## Dependencies
* Linux, VirtualBox or Docker Desktop
* Java 11
* Maven
* Git Config Server Repository  
* Spring Boot : 2.3.11.RELEASE
* Spring Cloud Api Gateway  
* Netflix Eureka Client
* Netflix Hystrix
* Google Container Tools


## Configuration 
src/main/docker/app.yml
```yaml
version: '3.8'
services:
  api-gateway:
    image: cevheri/api-gateway
    environment:
      - _JAVA_OPTIONS=-Xmx512m -Xms256m
    ports:
      - 9191:9191
```

We will use github public repository for our configuration:
https://github.com/cevheri/microservices-config-server


---
## Development
```shell
$ ./mvnw package
$ java -jar target/*.jar
```
Visit : http://localhost:8761/eureka/apps/api-gateway

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

Creating api-gateway ... done
```
---
Visit : http://localhost:8761/eureka/apps/api-gateway
```xml
<application>
    <name>API-GATEWAY</name>
    <instance>
        <instanceId>192.168.1.57:api-gateway:9191</instanceId>
        <hostName>service-registry</hostName>
        <app>API-GATEWAY</app>
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

REPOSITORY                TAG            IMAGE ID       CREATED             SIZE
cevheri/api-gateway       latest         57a100df8bcd   26 minutes ago      287MB

```

### View docker containers:
````shell
$ docker ps

CONTAINER ID   IMAGE                    COMMAND                  CREATED             STATUS          PORTS                                       NAMES
2ca74578a26c   cevheri/api-gateway      "bash -c /entrypoint…"   8 seconds ago       Up 6 seconds    0.0.0.0:9191->9191/tcp, :::9191->9191/tcp   api-gateway

````

### Stop Docker Compose:
```shell
$ docker-compose -f src/main/docker/app.yml down

```

