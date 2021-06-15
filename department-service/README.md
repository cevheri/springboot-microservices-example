# Java Microservices - Department Service


## Dependencies
* Linux, VirtualBox or Docker Desktop
* Java 11
* Maven
* Git Config Server Repository  
* Spring Boot : 2.5.0
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
  department-service:
    image: cevheri/department-service
    environment:
      - _JAVA_OPTIONS=-Xmx512m -Xms256m
      - APP_SLEEP=10 # for zipkin
    ports:
      - 9001:9001
  zipkin:
    image: openzipkin/zipkin
    ports:
      - 9411:9411
```

We will use github public repository for our configuration:
https://github.com/cevheri/microservices-config-server


---
## Development
```shell
$ ./mvnw package
$ java -jar target/*.jar
```
Visit : http://localhost:8761/eureka/apps/department-service

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

Creating department-service ... done
```
---
Visit : http://localhost:8761/eureka/apps/department-service
```xml
<application>
    <name>department-service</name>
    <instance>
        <instanceId>192.168.1.57:department-service:9001</instanceId>
        <hostName>service-registry</hostName>
        <app>department-service</app>
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

REPOSITORY                      TAG            IMAGE ID       CREATED             SIZE
cevheri/department-service      latest         57a100df8bcd   26 minutes ago      287MB

```

### View docker containers:
````shell
$ docker ps

CONTAINER ID   IMAGE                           COMMAND                  CREATED             STATUS          PORTS                                       NAMES
2ca74578a26c   cevheri/department-service      "bash -c /entrypoint…"   8 seconds ago       Up 6 seconds    0.0.0.0:9001->9001/tcp, :::9001->9001/tcp   department-service

````

### Stop Docker Compose:
```shell
$ docker-compose -f src/main/docker/app.yml down

```
