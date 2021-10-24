# REST API

## Routes based on the busines rules

### *Reminder*

Possible blocks:
* path
* query parameter
* http verbs: 
  * GET, POST, DELETE, PUT 
  * (unused in this api: HEAD, OPTIONS, PATCH, TRACE )

### Resources
* customers
* addresses
* accounts
* transactions
* credits

### Routes
#### customers

* GET /api/customer
  * get all customers 

* GET /api/customers/{secondname} 
  * get all customers of the banking systems by secondname
  * *sortable*

* POST /api/customers/
  * create a new customer
  * the body should contain:
    * firstname, secondname, salutation, date of birth, addressRequest, rating

* GET /api/customer/{id}/credit

### accounts

* GET /api/accounts/{customerId}
  * getAll accounts for given customerID 

* POST /api/accounts/{customerId}
  * create a new account for givven customerId

### transactions

* POST /api/transactions/{customerId}
  * add a new transaction for given customer
  * the body should contain:
    * the amount of money
    * the target account by id
    --> Trigger Posting

### credits
* POST /api/credits/{customerId}
  * Create a new credit for given customer

* GET /api/credits/{customerId}
  * List all credits of the given customer