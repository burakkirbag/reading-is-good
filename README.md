# ReadingIsGood App
It is a backend service developed by using SpringBoot and MongoDB.

#### Before you start, you need to register the application with ```/api/v1/customer``` url. After that you can create Bearer token to send request ```/api/v1/auth``` endpoint using email and password, then use the Bearer token to send request for each controller endpoints. Otherwise, you cannot access application.

# Controllers
* AuthenticationController
    * Login Customer
* CustomerController
    * Create New Customer
    * List Customer's Orders
* BookController
    * Create New Book
    * Book Detail
    * List Books
    * Update Book Stock
* OrderController
    * Create New Order
    * Order Detail
    * List Orders By Date Interval
* StaticsController
    * List Customer's Monthly Statics


## Used Technologies and Libraries
* Java 11
* SpringBoot 2.7.4
* Spring Data MongoDB
* Spring Security
* MongoDB
* Gradle
* JWT
* Lombok
* slf4j
* JUnit5
* assertj
* Swagger
* Docker
* Docker Compose

Swagger Documentation Address : http://localhost:8080/swagger-ui.html

## Run Commands
````shell script
    gradle clean build
    docker build -t reading-is-good-api .
    docker compose up
````