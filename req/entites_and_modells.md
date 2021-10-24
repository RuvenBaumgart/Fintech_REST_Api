# Models and Entities based on the business rules

## Entities

**customers** 
* id
* firstname
* secondname
* salutation
* day of birth
* addressEntity
* rating *default=2*

**address**
* id
* customerId
* city
* street
* province
* zip-code
* country

**accounts**
* id
* customerId
* balance
* transactionsCarriedOut
* List<String> transactionIds

**transactions (posting)**
* id
* accountId (source)
* destinationAccountId
* amount
* dateOfProcess

**credits**
* id
* customerId
* dateOfCreation
* originalTerm (Laufzeit)
* remainingTerm (Restlaufzeit)
* originalCreditAmount
* currentCeditAmount


## Models 
### Requests
**transaction**
* amount
* destination

**customers**
* firstname
* secondname
* salutation
* dateOfBirth(dd/mm/yyyy)
* addressRequest

**address**
* city
* street
* province
* zip-code
* country

### Responses

**customersNameAndAndress**
* firstname
* secondname
* salutation
* addressResponse

**customerRated**
* firstname
* secondename
* salutation
* rating

**address**
* city
* street
* province
* zip-code
* country

**accountsWithBalance**
* id
* balance

**credit**
* id
* originalterm
* remainingterm
* originalCreditAmount
* currentCreditAmount

**creditExceeded**
* orignial credit amount
* current credit amount
* fistname
* lastname

**transactionsFull**
* id
* accountId(source)
* destinationAccountId
* amount
* dateOfProcess

**transactionsCustomer**
* accountId
* customerName
* accountIdDestination
* customerNameDestination


### Linkage (foreign Key Possible)

* customer <---> address
* customer <--- account
* account <---> transactions
* customer <---  credits

