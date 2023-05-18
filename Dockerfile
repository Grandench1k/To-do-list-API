FROM maven:latest

MAINTAINER Grandench1k

COPY ./ ./

RUN mvn clean package -DskipTests

ENTRYPOINT ["java", "-jar", "target/To-do-list-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080
