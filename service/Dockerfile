
FROM openjdk:8-jre-alpine
RUN mkdir -p /opt/
#ADD target/ /opt/
WORKDIR /opt

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN ./mvnw dependency:go-offline
 
COPY src ./src
 
CMD ["./mvnw", "spring-boot:run"]
#EXPOSE 8080
#EXPOSE 8090

#ENTRYPOINT exec java -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap $JAVA_OPTS -jar employee-service.jar
