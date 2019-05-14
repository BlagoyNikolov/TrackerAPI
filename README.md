# Finance Tracker API

The FinanceTracker is a web based Java Application which helps you manage your bank accounts.
It keeps track of all your cash inflow and outflow and collects information about your financial wealth.
The app also provides tools that would help you build a financial plan.
All of the information is presented via cutting edge technologies in software and design.

## Features

* Add multiple payment instruments per user (bank accounts, credit cards, cash, etc.)
* Log transactions related to accounts
* Create personal categories (deposit or withdrawal)
* Execute transfers of funds between accounts
* Make budgets for a certain period, account and value
* Create planned payments (will be executed at a specified date)

## Technologies used

* Java EE
* Spring Boot
* Spring Data (JPA + Hibernate)
* Spring Security
* MySQL
* Liquibase
* Lombok
* MapStruct
* Gradle
* Swagger
* IntelliJ IDEA IDE
* Git

The API itself is fully HATEOAS compliant.

## How to set up and run?

* Build with the Gradle wrapper.
* Create a database schema called "finance_tracker_hibernate"
* The database structure as well as initial data insertion is handled by Liquibase on app startup.