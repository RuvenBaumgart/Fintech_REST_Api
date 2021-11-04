# Open Points
## Open requirements
### General 
* self-contained, buildable on a third-party computer and fully production ready.
* tested fully automatically 
* relational memory database as persistence unit 
  * *during development h2 is used. Product ready will contain MySQL*


### Bussines Logic
* The first booking should be configurable in the properties of the application.
* Process to the next booking date. A booking da teshould be every weekday regardless of holidays.
* If a customer paid off a credit he will be awarded to a better rating class but at maximum to ‚1‘.
* If a customer didn‘t paid off a credit before the remaining term is below zero his rating class will be set to ‚4‘.
