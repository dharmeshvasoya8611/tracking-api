Tracking API :

Pre-requisites
	Java 17
	Maven

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

API URL
	"http://172.203.222.73:80/tracking/api/next-tracking-number?origin_country_id=MY&destination_country_id=ID&weight=1.234&created_at=2018-11-20T19:29:32+08:00&customer_id=de619854-b59b-425e-9db4-943979e1bd49&customer_name=RedBox Logistics&customer_slug=redbox-logistics"
	
API Auth
	No Auth
	
Detailed Info
	Review the "tracking-api_doc.docx"
	
Logic to generate 16 digit unique tracking number

	-- originCountryId + current day + destinationCountryId + last two digit of host ip address + last four digit of customerId + last four digit of current ms time (System.currentTimeMillis())
	-- convert generated tracking number string to upper case
	-- Regex: "^[A-Z0-9]{16}$"
	
API Mandatory Query Params
	-- originCountryId
	-- destinationCountryId
	-- customerId
	