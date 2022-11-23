To launch the Spring Boot application:
1) Ensure you have JDK 17 installed and JAVA_HOME environment variable pointing to the JDK 17 folder
   (e.g.:`export JAVA_HOME=C:\tmp\jdk-17`)
2) In the root folder of this project, execute the following command line:
   `./mvnw spring-boot:run`


This will run the Spring Boot server on `http://localhost:8080` by default

You can then execute HTTP requests such as:
`curl -X POST http://localhost:8080/api/transfer -H 'Content-Type: application/json' -d '{...}'` (replace {...} with the bulk transfer instructions in JSON format)

To change the server port, go to `application.properties` and change the `server.port` value