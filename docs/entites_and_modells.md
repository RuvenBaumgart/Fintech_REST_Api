# Models and Entities based on the business rules

## Entities

**users**
* userId
* username
* hashedPassword

**customers** 
* id
* firstname
* secondname
* salutation
* day of birth
* adressId
* rating *default=2*
@OneToOne
* addressEntity address

**address**
* id
* customerId
* city
* street
* province
* zip-code
* country
@OneToOne
* customerEntity customer

**accounts**
* id
* customerId
* balanceInCent
* sumOfTransactions
* List<String> transactionIds
@OneToMany
* List<TransactionsEntity> transactions

**transactions (posting)**
* id
* sourceAccountId
* destinationAccountId
* amountInCent
* transactionDate
* transactionTime
* message
@ManyToOne
* accountEntity accounts

**credits**
* id
* customerId
* dateOfCreation
* originalTermInMonths(Laufzeit) *Maybe it should be changed to days*
* remainingTermInMonths(Restlaufzeit)
* originalCreditAmountInCents
* currentCreditAmountInCents *if currentCreditAmountInCents eq. orignialCreditAmountInCents then nothing is payed back. When currentCreditAmountInCents is eq to zero than everything is payed back*


## Models 
### Requests

**AuthentificationRequest**
* username
* password

**CreditRequest**
* creditAmountInCents
* runtimeInMonths

**CreditUpdateRequest**
* amountInCent
* sourceAccountId


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

**account**
* id
* cutomerId
* balanceInCent
* sumOfTransactions
* transactionIds


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
* sourceAccountId
* customerFirstName
* customerSecondName
* accountIdDestination
* customerFirstName
* customerSecondName

### Linkage (foreign Key Possible)

* customer  <---  address 
* customer  <---  account
* account   <--- transactions
* customer  <---  credits

