# rest-demo
REST project for learning purpose

# Requests with POSTMAN
Auth Type : Basic Auth

Username : "user"
Password : "password"

Username : "admin"
Password : "password"


#Login with Google credentials
1. Comment SecurityConfig.java file
2. Uncomment this 4 lines
spring.security.oauth2.client.registration.google.client-id= INSERT_YOUR_API_CLIENT_ID
spring.security.oauth2.client.registration.google.client-secret= INSERT_YOUR_API_CLIENT_SECRET (This is not valid. For example only)
spring.security.oauth2.client.registration.google.scope=openid
spring.security.oauth2.client.registration.google.redirect-uri=http://localhost:8080/login/oauth2/code/google
   
Get Credentials from: https://console.cloud.google.com/apis/dashboard?project=my-rest-test-project-436009

Open Chrome and log in with Google credentials for example:
http://localhost:8080/users/5

# Get POSTMAN requests 
src/main/resources/postman/rest-demo.postman_collection_26.09.2024.json

# Create CarController object 
CarController.getAllCars() method contains piece of code which create Authentication object for learning purpose!!!

Important points:
1. Rest controller communication
2. Spring Data JPA
3. Implemented some Unit tests 
4. Implemented some Integration tests
5. Spring Actuator
6. jacoco-maven-plugin for test coverage.
7. In memory UserDetailsService
8. Filter

