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
spring.security.oauth2.client.registration.google.client-id=
spring.security.oauth2.client.registration.google.client-secret=
spring.security.oauth2.client.registration.google.scope=openid
spring.security.oauth2.client.registration.google.redirect-uri=http://localhost:8080/login/oauth2/code/google

Open Chrome and log in with Google credentials for example:
http://localhost:8080/users/5

# Get POSTMAN requests 
src/main/resources/postman/rest-demo.postman_collection_26.09.2024.json

# Create CarController object 
CarController.getAllCars() method contains piece of code which create Authentication object for learning purpose!!!
