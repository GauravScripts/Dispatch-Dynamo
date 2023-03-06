FROM openjdk:11
ADD ./target/shipping-services-0.0.1-SNAPSHOT.jar /usr/src/shipping-services-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "shipping-services-0.0.1-SNAPSHOT.jar"]
