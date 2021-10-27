# Models and Entities based on the business rules

## Entities

**customers** 
* id
* firstname
* secondname
* salutation
* day of birth
* adressId
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
* balanceInCent
* sumOfTransactions
* List<String> transactionIds

**transactions (posting)**
* id
* sourceAccountId
* destinationAccountId
* amountInCent
* transactionDate
* transactionTime
* message

**credits**
* id
* customerId
* dateOfCreation
* originalTermInMonths(Laufzeit) *Maybe it should be changed to days*
* remainingTermInMonths(Restlaufzeit)
* originalCreditAmountInCents
* currentCeditAmountInCents


## Models 
### Requests
**transaction**
* amountInCent
* sourceAccoundId
* destinationAccountId
* message

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
* zipCode
* country


### Responses

**customersNameAndAdress**
* customerId
* firstname
* secondname
* salutation
* rating
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
* balanceInCents

**credit**
* id
* customerId
* originaltermInMonths
* remainingtermInMonths
* originalCreditAmountInCents
* currentCreditAmountInCents

**creditExceeded**
* orignialCreditAmountInCents
* currentCreditAmountInCents
* fistname
* lastname

**transactionsFull**
* id
* sourceAccountId
* destinationAccountId
* amountInCent
* transactionDate
* transactionTime

**transactionsCustomer**
* accountId
* customerFirstName
* customerSecondName
* accountIdDestination
* customerFirstName
* customerSecondName

### Linkage (foreign Key Possible)

* customer  <---  address *unfortunately the way to the customer names is long starting from transactions*
* customer  <---  account
* account   <--- transactions
* customer  <---  credits

