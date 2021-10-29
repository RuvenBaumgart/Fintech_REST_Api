# Open Points
## Open requirements
### General 
* self-contained, buildable on a third-party computer and fully production ready.
* tested fully automatically 
* relational memory database as persistence unit 
  * *during development h2 is used. Product ready will contain MySQL*
* The application should be secured with a login backed with only one hardcoded user and password combination. If a user is not logged he should not have access to the system.

### Bussines Logic
* List all postings with the account id and customer name of source and destination of one
customer and make the result sortable and page-able.
* The first booking should be configurable in the properties of the application.
* Process to the next booking date. A booking da teshould be every weekday regardless of holidays.
* If a customer paid off a credit he will be awarded to a better rating class but at maximum to ‚1‘.
* If a customer didn‘t paid off a credit before the remaining term is below zero his rating class will be set to ‚4‘.

## Next Steps
### General
* Complet remaing open points
* Create secure log in
* Create MySql Server
* make the application deployable