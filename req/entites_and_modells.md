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
* transactionsAmount
* transactionsEntity

**transactions**
* id
* accountId
* amount
* source 
* destination
* date

**credits**
* id
* customerId
* date
* originalTerm (Laufzeit)
* remainingTerm (Restlaufzeit)
* originalCreditAmount
* currentCeditAmount


**postings**
* id



## Models 
### Requests
**transaction**
* amount
* destination

**customers**
* firstname
* secondname
* salutation
* date of birth
* addressRequest
* rating

**address**
* city
* street
* province
* zip-code
* country

### Response



