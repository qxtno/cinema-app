FROM openjdk:17.0.2
COPY target/cinema-app-0.0.1-SNAPSHOT.jar /cinema-app-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/cinema-app-0.0.1-SNAPSHOT.jar"]