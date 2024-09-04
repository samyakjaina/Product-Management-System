
# Product Management System
This project is a Product Management System built with Spring Boot, JWT-based Spring Security, Java 8, Hibernate, and JPA. It includes basic CRUD operations for managing products and follows best practices for design patterns, such as repository, service, controller, and exception handler layers. The project also includes test cases and uses Swagger for API documentation.

## Features

These are the features :

- JWT-based Authentication and Authorization
- CRUD operations for Products
- Proper Layered Architecture (Controller, Service, Repository)
- Exception Handling
- Hibernate and JPA for Data Persistence
- Swagger Integration for API Documentation
- Unit and Integration Tests

## Technologies Used

- Java 8
- Spring Boot
- Spring Security with JWT
- Hibernate & JPA
- Maven
- Swagger
- JUnit

## Prerequisites

- Java 8+
- Maven 3.6+
- MySQL or any relational database
- Postman or any REST client (for testing purposes)
- Git

## Installation
- Clone the repository: `git clone https://github.com/samyakjaina/Product-Management-System.git`
- Navigate to the project directory: `cd product-management-system`
- Build the project using Maven: `mvn clean install`
- Run the application: `mvn spring-boot:run` or ` you can use eclipse for starting the application `

## Configuration
Configuration details, such as database settings and security credentials, can be set in the src/main/resources/application.properties file.

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver     
spring.datasource.url=jdbc:mysql://localhost:3306/sys?allowPublicKeyRetrieval=true&useSSL=false          
spring.datasource.username=root            
spring.datasource.password=root          
spring.jpa.show-sql=true           
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect          
spring.jpa.hibernate.ddl-auto=update         

secret=somerandomsecret       
token.validity=36000           

## API Documentation
Swagger is integrated into the project for API documentation. After running the application, you can access the Swagger UI at: `http://localhost:8080/swagger-ui.html` 
This provides a user-friendly interface for testing the API endpoints.

##Testing
Unit and integration tests are included in the project. To run the tests, use the following Maven command: `mvn test` or ` you can just run the test file manually` 

##Contact
For questions or support, please contact `samyakjain2732000@gmail.com`.

