Tracking API :

To run the application on linux (run profile based on environment)
	"./mvnw spring-boot:run  -Dspring-boot.run.profiles=local -pl application"
	"./mvnw spring-boot:run  -Dspring-boot.run.profiles=uat -pl application"
	"./mvnw spring-boot:run  -Dspring-boot.run.profiles=prod -pl application"
	
To run the application on windows (run profile based on environment)
	"mvnw spring-boot:run  -Dspring-boot.run.profiles=local -pl application"
	"mvnw spring-boot:run  -Dspring-boot.run.profiles=uat -pl application"
	"mvnw spring-boot:run  -Dspring-boot.run.profiles=prod -pl application"

To format the code (linux)
	"./mvnw spotless:apply"

 To format the code (windows)
	"mvnw spotless:apply"
