FROM openjdk:11
ADD ./target/vendor-service-0.0.1-SNAPSHOT.jar /usr/src/vendor-service-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "vendor-service-0.0.1-SNAPSHOT.jar"]
