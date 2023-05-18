FROM maven:latest

MAINTAINER Grandench1k

COPY target/To-do-list-0.0.1-SNAPSHOT.jar To-Do-List.jar

ENTRYPOINT ["java", "-jar", "To-Do-List.jar"]
