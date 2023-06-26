FROM openjdk:11
WORKDIR usr/lib
EXPOSE 8181
ADD ./target/patienceManagementSystem-0.0.1-SNAPSHOT.jar /usr/lib/patienceManagementSystem-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "patienceManagementSystem-0.0.1-SNAPSHOT.jar"]
