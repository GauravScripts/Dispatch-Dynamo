FROM openjdk:11
ADD ./target/user-profile-0.0.1-SNAPSHOT.jar /usr/src/user-profile-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "user-profile-0.0.1-SNAPSHOT.jar"]
