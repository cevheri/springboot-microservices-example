# Java Microservices - Person Service


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
  person-service:
    image: cevheri/person-service
    environment:
      - _JAVA_OPTIONS=-Xmx512m -Xms256m
      - APP_SLEEP=10 # for zipkin
    ports:
      - 9002:9002
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
Visit : http://localhost:8761/eureka/apps/person-service

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

Creating person-service ... done
```
---
Visit : http://localhost:8761/eureka/apps/person-service
```xml
<application>
    <name>PERSON-SERVICE</name>
    <instance>
        <instanceId>192.168.1.57:person-service:9002</instanceId>
        <hostName>service-registry</hostName>
        <app>PERSON-SERVICE</app>
        <ipAddr>192.168.1.57</ipAddr>
        <status>UP</status>
        <overriddenstatus>UNKNOWN</overriddenstatus>
        <port enabled="true">9002</port>
        <securePort enabled="false">443</securePort>
        <countryId>1</countryId>
        <dataCenterInfo class="com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo">
            <name>MyOwn</name>
        </dataCenterInfo>
        <leaseInfo>
            <renewalIntervalInSecs>30</renewalIntervalInSecs>
            <durationInSecs>90</durationInSecs>
            <registrationTimestamp>1623793389087</registrationTimestamp>
            <lastRenewalTimestamp>1623793539062</lastRenewalTimestamp>
            <evictionTimestamp>0</evictionTimestamp>
            <serviceUpTimestamp>1623793388576</serviceUpTimestamp>
        </leaseInfo>
        <metadata>
            <management.port>9002</management.port>
        </metadata>
        <homePageUrl>http://service-registry:9002/</homePageUrl>
        <statusPageUrl>http://service-registry:9002/actuator/info</statusPageUrl>
        <healthCheckUrl>http://service-registry:9002/actuator/health</healthCheckUrl>
        <vipAddress>person-service</vipAddress>
        <secureVipAddress>person-service</secureVipAddress>
        <isCoordinatingDiscoveryServer>false</isCoordinatingDiscoveryServer>
        <lastUpdatedTimestamp>1623793389087</lastUpdatedTimestamp>
        <lastDirtyTimestamp>1623793388537</lastDirtyTimestamp>
        <actionType>ADDED</actionType>
    </instance>
</application>
```

---
### View docker images:
```shell
$ docker images

REPOSITORY                   TAG            IMAGE ID       CREATED             SIZE
cevheri/person-service       latest         57a100df8bcd   26 minutes ago      287MB

```

### View docker containers:
````shell
$ docker ps

CONTAINER ID   IMAGE                      COMMAND                  CREATED             STATUS          PORTS                                       NAMES
2ca74578a26c   cevheri/person-service      "bash -c /entrypoint…"   8 seconds ago       Up 6 seconds    0.0.0.0:9002->9002/tcp, :::9002->9002/tcp   person-service

````

### Stop Docker Compose:
```shell
$ docker-compose -f src/main/docker/app.yml down

```

---
### Screenshots

![](files/pictures/eureka-person-service.png)