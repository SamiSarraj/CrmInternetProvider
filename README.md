# CrmInternetProvider
A web-based system for managing the relation between customers and ISP company. This include an complaint system for customers for raising complaints on the issues related to ISP provider and ordered internet packages.
System goal is to improve business relationships, help companies stay connected to customers, streamline processes and improve firms profitability.
By collecting information that identifies customers' buying habits, including preferences and frequency, CRM systems give businesses a closer look at their customers' wants and needs so they can provide better customer service solutions.

## Main Functionality

● Users system management (adding, modifying, deleting, role, rights etc)

● Requests(tickets) to raise customers complaints

● Requests(tickets) processing unit

● Internet packages plan management (creating, modifying, deleting etc)

● Internal Help desk for employees

● General and specialized statics

● Generating sensible graphs

● Generating reports

● Rating system

● Customer capability of managing own internet plan

● Built-in messaging service between customer and employee

● Requests archive history

● Customer actions history



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

1 - The first step is to prepare the database schema in MySQL, with the name `crm`. The schema should be empty, the application itself takes care of creating tables and other elements.

2 - a. Running BackEnd only: 

To run the Spring Boot application on your local machine. One way is to execute the `main` method from your IDE. The Api will be exposed at http://localhost:8080/crm.

   b. Running whole application:
   
firstly u excute the BackEnd step then run the FrontEnd node.js server with the follow command `npm start`. The application will be up and running at localhost http://localhost:4200/crmNet

### Visual examples of the running app

* Adding new client:

<img src="https://github.com/SamiSarraj/CrmInternetProvider/blob/master/Img/AddCustomer(1).png" width="350" title="hover text">

* Customer list:

<img src="https://github.com/SamiSarraj/CrmInternetProvider/blob/master/Img/ListCusotmer(2).png" width="350" title="hover text">

* Adding new ticket:

<img src="https://github.com/SamiSarraj/CrmInternetProvider/blob/master/Img/AddTicket(1).png" width="350" title="hover text">

* Employee profile: 

<img src="https://github.com/SamiSarraj/CrmInternetProvider/blob/master/Img/OcenPrac(2).png" width="350" title="hover text">

To get a complete and detailed presentation of the application features or if you have any question please contact me at: samisarraj94@gmail.com.

