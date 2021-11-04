# FINTECH EXAMPLE PROJECT
## Microservice located in the financial sector

### Application Login
  * The Backend is protected by a hardcoded user. Please use the following credentials:
    * *username = root*
    * *password = admin*
  * Use the Route specified in *docs/rest_api.md*
    * /api/authentification
  * and send the credentials in the body
  * after that you will receive a token. Please copy the token in each request to the api in the header
    * implement the token in the value of the header. The key should be called "Authorization"
    * check for examples in the postman_collection.json

### REST API
  * Please check the document *rest_api.md* in the docs folder