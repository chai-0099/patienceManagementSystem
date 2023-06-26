FROM openjdk:11
WORKDIR usr/lib
EXPOSE 8181
ADD ./target/patientManagementSystem-0.0.1-SNAPSHOT.jar /usr/lib/patientManagementSystem-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "patientManagementSystem-0.0.1-SNAPSHOT.jar"]
