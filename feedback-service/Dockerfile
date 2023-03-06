FROM openjdk:11
ADD ./target/feedback-service-0.0.1-SNAPSHOT.jar /usr/src/feedback-service-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "feedback-service-0.0.1-SNAPSHOT.jar"]
