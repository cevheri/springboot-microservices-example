
# jib build images
cd ~/projects/springboot-microservices-example/service-registry   && ./mvnw -Pprod clean verify jib:dockerBuild && cd ~
cd ~/projects/springboot-microservices-example/config-server      && ./mvnw -Pprod clean verify jib:dockerBuild && cd ~
cd ~/projects/springboot-microservices-example/api-gateway        && ./mvnw -Pprod clean verify jib:dockerBuild && cd ~
cd ~/projects/springboot-microservices-example/hystrix-dashboard  && ./mvnw -Pprod clean verify jib:dockerBuild && cd ~
cd ~/projects/springboot-microservices-example/department-service && ./mvnw -Pprod clean verify jib:dockerBuild && cd ~
cd ~/projects/springboot-microservices-example/person-service     && ./mvnw -Pprod clean verify jib:dockerBuild && cd ~
cd ~/projects/springboot-microservices-example/task-service       && ./mvnw -Pprod clean verify jib:dockerBuild && cd ~

# network config
docker network create ms-network
docker network connect ms-network service-registry #or run

# run images
docker run -d --network ms-network -p 8761:8761 --name service-registry cevheri/service-registry:latest
docker run -d --network ms-network -p 9296:9296 --name config-server cevheri/config-server
docker run -d --network ms-network -p 9191:9191 --name api-gateway cevheri/api-gateway:latest
docker run -d --network ms-network -p 9295:9295 --name hystrix-dashboard cevheri/hystrix-dashboard:latest
docker run -d --network ms-network -p 9001:9001 --name department-service cevheri/department-service:latest
docker run -d --network ms-network -p 9002:9002 --name person-service cevheri/person-service:latest
docker run -d --network ms-network -p 9003:9003 --name task-service cevheri/task-service:latest

# set docker tag and docker push hub.docker remote repository
docker tag cevheri/service-registry:latest cevheri/service-registry:latest
docker tag cevheri/service-registry:latest cevheri/service-registry:v1.2.0
docker push cevheri/service-registry:v1.2.0
docker push cevheri/service-registry:latest

docker tag cevheri/config-server:latest cevheri/config-server:latest
docker tag cevheri/config-server:latest cevheri/config-server:v1.2.0
docker push cevheri/config-server:v1.2.0
docker push cevheri/config-server:latest

docker tag cevheri/api-gateway:latest cevheri/api-gateway:latest
docker tag cevheri/api-gateway:latest cevheri/api-gateway:v1.2.0
docker push cevheri/api-gateway:v1.2.0
docker push cevheri/api-gateway:latest

docker tag cevheri/hystrix-dashboard:latest cevheri/hystrix-dashboard:latest
docker tag cevheri/hystrix-dashboard:latest cevheri/hystrix-dashboard:v1.2.0
docker push cevheri/hystrix-dashboard:v1.2.0
docker push cevheri/hystrix-dashboard:latest

docker tag cevheri/department-service:latest cevheri/department-service:latest
docker tag cevheri/department-service:latest cevheri/department-service:v1.2.0
docker push cevheri/department-service:v1.2.0
docker push cevheri/department-service:latest

docker tag cevheri/person-service:latest cevheri/person-service:latest
docker tag cevheri/person-service:latest cevheri/person-service:v1.2.0
docker push cevheri/person-service:v1.2.0
docker push cevheri/person-service:latest

docker tag cevheri/task-service:latest cevheri/task-service:latest
docker tag cevheri/task-service:latest cevheri/task-service:v1.2.0
docker push cevheri/task-service:v1.2.0
docker push cevheri/task-service:latest
