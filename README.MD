## Authentication Service

This service will do the following

-  Generate User & admin JWT Token by invoking following GET endpoints
   - http://localhost:8081/authentication/generate/userToken
   - http://localhost:8081/authentication/generate/adminToken

-  Validate JWT token (POST Call) - This api will take the Authorization Header as an input and will return the role that the token has.
   - http://localhost:8081/authentication/validateToken

Run the Main Application **AuthenticationServiceApplication.java** and verify if you can access the above endpoints.