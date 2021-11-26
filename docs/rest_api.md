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
* users

### Routes

#### users
* POST /api/authentification
  * The body should contain:
    * username:username 
    * password:password
  
#### customers

* GET /api/customers
  * get all customers

* GET /api/customers/{secondname} 
  * get all customers of the banking systems by secondname with address
  
* GET /api/customers/{secondname}?sort=tag 
  * get all customers of the banking system by secondname with address
  * and allow to sort the result (default by firstname)

* GET /api/customers/grouped?grouped=groupedBy
  * List all customers with name and  grouped by their current rating class
  
* POST /api/customers/
  * create a new customer wit firstname, secondname, date of birth, address and rating.
  * the body should contain:
    * firstname, secondname, salutation, date of birth, addressRequest, rating
 

#### accounts

* GET /api/accounts/{customerId}
  * list all accounts of one customer with their current balance

* GET /api/accounts/{cusomterId}/balance
  * show the balance for one customers considering all accounts of the customer

* GET /api/accounts/balance
  * show the cummulated balance of all account for the financial instituation

* POST /api/accounts/{customerId}
  * create a new account for given customerId

* PUT /api/accounts/{customerId} *not implemented*
  * update the balance on the account


#### transactions (postings)

* GET /api/transactions?date=date
  * List all postings(transactions) of the financial institution for a given date

* GET /api/transactions/{customerId}/?pageno=pageNo&pagesize=pageSize&sortby=sortBy
  * results are *pageable* and *sortable* by querystrings
  * List all postings for the given customer
    * The queryString should contain:
      * pageNo, default = 0
      * pageSize, default = 10
      * sortBy, default = sender_firstname
        *Current Sort Options:  
          * sender_firstname
          * sender_secondname
          * recepient_firstname
          * recepient_secondname


* POST /api/transactions
  * add a new transaction for given customer (transfer money from one accoutn to another)
  * the body should contain:
    * the amount of money in Cents
    * the source account by id
    * the target account by id

#### credits
* GET /api/credits/{customerId}
  * List all credits of the given customer

* GET /api/credits?tag=originalTermExceeded
  * List all credits with original credit amount, current credit amount and customer name which are exceeded their original terms.

* POST /api/credits/{customerId}
  * Create a new credit for given customer
    * The RequestBody should contain:
      * credit volume in cents
      * runtime in Months or term of the credit

* PUT /api/credits/{id}
  * Update or Payoff a part of a credit by transfering money from an account and should trigger an new transaction.
    * The body should contain
      * the amount of payoff 
      * the sourceAccount from which to pay

### authentification
* POST /api/authentification
  * authentificate a user based on the given credentials
    * CREDENTIALS
      * username
      * password