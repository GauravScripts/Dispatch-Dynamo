FROM openjdk:11
ADD ./target/MailServiceApp-0.0.1-SNAPSHOT.jar /usr/src/MailServiceApp-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "MailServiceApp-0.0.1-SNAPSHOT.jar"]
