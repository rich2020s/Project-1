# Expense Reimbursement System (ERMS)

## Project Description
For Project One, we built a REST API that satisfies the requirements set forth in the Expense Reimbursement Management System document. All Data Structures used were custom implemented (Custom list and Custom Array List), and we created discrete Data Access layer. Finally API was demonstrated using Postman.

## Technologies
* RESTful API built with Servlets
* Unit testing with JUnit (At least 90% test coverage (line coverage) for the data and service layers)
* Data stored in local PostgreSQL database

## Features
List of features

* As an employee I can register for a new account.
* As an employee I can login.
* As an employee I can submit my tickets for reimbursement.
* As an employee I can view my past tickets.
* As an employee I can view pending tickets.

* As a manager I can view all pending requests.
* As a manager I can view past tickets for all employees in the company.
* As a manager I can accept or deny reimbursement requests.


## Getting Started
1. `git clone git@github.com:rich2020s/Project-1.git`
4. Open the file on IntelliJ.
5. (Under src/main) Create a resources folder and dbConfig.properties file inside the folder. 
7. Configure the dbConfig with the local Postgres database.
```
url = jdbc:postgresql://localhost:5432/yourDB
username = {your username}
password = {your password}
```
6. navigation -> run -> edit configurations -> add New Configuration(+) -> Smart Tomcat. Depolyment directory is `Project-1/src/main/webapp`. Then Click OK.
8. Run TomCat configuration to start the program.
9. Test the functionalities using Postman.
  
(include all environment setup steps)

Be sure to include BOTH Windows and Unix command
Be sure to mention if the commands only work on a specific platform (eg. AWS, GCP)

* All the code required to get started
* Images of what it should look like
## Usage
Test the API functionalities using Postman. <should we include any screenshots?>

## Contributors
Dae Hyun Kim  
Rich Chan  

