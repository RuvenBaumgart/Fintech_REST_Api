# REST API

## Routes based on the busines rules

### *Reminder*

Possible blocks:
* path
* query parameter
* http verbs: 
  * GET, POST, DELETE, PUT 
  * (unused in this api: HEAD, OPTIONS, PATCH, TRACE )
  * GET retrieves resources.
  * POST submits new data to the server.
  * PUT updates existing data.
  * DELETE removes data.

### Resources
* customers
* accounts
* transactions (posting)
* credits

### Routes
#### customers

* GET /api/customers
  * get all customers

* GET /api/customers/{secondname} 
  * get all customers of the banking systems by secondname.
  * *sortable*
  

* POST /api/customers/
  * create a new customer
  * the body should contain:
    * firstname, secondname, salutation, date of birth, addressRequest, rating
 
* GET /api/customers?tag=rating
  * List all customers with name grouped by their current rating class

### accounts

* GET /api/accounts/{customerId}
  * list all accounts of one customer with their current balance

* GET /api/accounts/{customerId}?tag=balance
  * show the balance for one customers

* GET /api/accounts/
  * show the balance for the financial instituation

* POST /api/accounts/{customerId}
  * create a new account for givven customerId


### transactions

* POST /api/transactions/{customerId}
  * add a new transaction for given customer (transfer money from one accoutn to another)
  * the body should contain:
    * the amount of money
    * the target account by id

* GET /api/transactions/{date}
  * List all postings(transactions) of the financial institution for a given date

* GET /api/transactions/{customerId}
  * List all postings 

### credits
* POST /api/credits/{customerId}
  * Create a new credit for given customer

* GET /api/credits/{customerId}
  * List all credits of the given customer

* PUT /api/credits/{customerId}
  * Update or Payoff a part of a credit by transfering money from an account
    * The body should contain the amount of payoff and should trigger an new transaction

* GET /api/credits?tag=originalTermExceeded
  * List all credits with original credit amount, current credit amount and customer name which are exceeded their original terms.
