# rest-demo
REST API for learning purpose

# Important points:
1. Rest controller communication.
2. Spring Data JPA.
3. Implemented some Unit tests.
4. Implemented some Integration tests.
5. Spring Boot Actuator.
   After every GET http://localhost:8080/cars/all request, the count of new registered custom metric
   http://localhost:8080/actuator/metrics/cars.all.count is incremented.
6. jacoco-maven-plugin for test coverage.
7. In memory UserDetailsService.
8. Implement Filter.
9. Login with Google credentials.

# Requests
Auth Type : Basic Auth

Username : "user"
Password : "password"

Username : "admin"
Password : "password"

# Login with Google credentials
1. Comment SecurityConfig.java file
2. Uncomment this 4 lines
spring.security.oauth2.client.registration.google.client-id= INSERT_YOUR_API_CLIENT_ID
spring.security.oauth2.client.registration.google.client-secret= INSERT_YOUR_API_CLIENT_SECRET
spring.security.oauth2.client.registration.google.scope=openid
spring.security.oauth2.client.registration.google.redirect-uri=http://localhost:8080/login/oauth2/code/google
   
Get Credentials from: https://console.cloud.google.com/apis/dashboard?project=my-rest-test-project-436009

Open Chrome and log in with Google credentials for example:
http://localhost:8080/users/5

# Create Authentication object
CarController.getAllCars() method contains piece of code which create Authentication object for learning purpose!!!
