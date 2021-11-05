# FINTECH EXAMPLE PROJECT
## Microservice located in the financial sector

### Description
This REST API is a test project to simulate a partial backend system wihtin the context of an institute in the financial sector. The business logic might not reflect a real banking system but should come close. Furthermore the business logic of this program or application for sure is not complete and can be extensible.  

### Used Tackstack
  * Spring Framework
  * JUnit
  * Maven
  * Hibernate
  * H2 as persistence unit to test repositories and handwritten queries
  * MySQL for the production 
  * JSON Web Token
  * Docker 
  * Postman for testing

### Application Login
  * The Backend is protected by a hardcoded user. If you have donwloaded the code and want to test the api please use the following credentials or change it in the codebase:
    * *username = root*
    * *password = admin*

  * Use the Route specified in *docs/rest_api.md*
    * for authentification it is */api/authentification*
  * send the credentials in the body as json object.
  * if the authentification process is successful you will reveice the jwt. Please copy the token in each request to the api in the header
    * implement the token in the value of the header. The key should be called "Authorization"
    * check for examples in the postman_collection.json

### REST API
  * Please check the document *rest_api.md* in the docs folder


### START THE APP
  * To start the application you need to install docker if you not already have a working environement
  * After that move to the main folder that is containing the docker-compose.yaml file.
  * run the command *docker-compose up*
  * The Repo is containing a Postman Collection. You can download and start postman, import the postman collection and use it as testing purpose or you can call the api via an indivudal way
  * Get the jwt
  * implement the jwt in the header and then you should be able to use the api.