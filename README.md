# CrmInternetProviderBackend
A CRM system for an Internet network provider

## Technologies

FrontEnd:
* Angular CLI 6.0.7
* BootStrap 4.1.3
* jQuery 1.12.4
* Plugins - Shinydashboard, Nestable, Lopipanel, iCheck, Dropzonejs, DataTables, FooTable, Chart.js, Font Awesome, Themify icons, Full Calendar, Summernote, SlimScroll, Pace i fastclick.

The FrontEnd was bought and downloaded as an HTML template then it was completly refactored by me to fit into Angular. The FrontEnd was mainly developed in WebStorm 18.2.3.

BackEnd:
* Spring Boot 1.5.8
* Spring Security
* MySQL 8.0.13
* Maven 3
* Java 8

The BackEnd was mainly developed in IntelliJ IDEA 2017.

## Running the application locally

1 - The first step is to prepare the database schema in MySQL, with the name 'crm'. The schema should be empty, the application itself takes care of creating tables and other elements.

2 - a. Running BackEnd only: 

To run the Spring Boot application on your local machine. One way is to execute the `main` method from your IDE. The Api will be exposed at http://localhost:8080/crm.

    b. Running whole application:

firstly u excute the BackEnd step then run the FrontEnd node.js server with the follow command " npm start ". The application will be up and running at localhost http://localhost:4200/crmNet

### Picture of the running app








