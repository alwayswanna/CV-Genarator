FROM openjdk:17.0
ADD cv-backend/target/cv-backend-0.0.1-SNAPSHOT.jar cv-backend-0.0.1-SNAPSHOT.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "BusStation-0.0.1-SNAPSHOT.jar"]