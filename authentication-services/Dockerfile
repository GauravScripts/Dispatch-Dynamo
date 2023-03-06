FROM openjdk:11
ADD ./target/authentication-services-0.0.1-SNAPSHOT.jar /usr/src/authentication-services-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "authentication-services-0.0.1-SNAPSHOT.jar"]
