# About #
This is a Spring Boot project which offers basic CRUD functionality for accessing a PostgreSQL database containing "products".
It uses the JPA and PostgreSQL libraries to make an automatic connection to the database. If the table does not exist locally then the app will create a temporary one automatically.

# User Guide #
In order to run the project you will need:
1. A Java compiling software such as IntelliJ or Eclipse
2. JDK installation of at least Java 21 LTS (can be downloaded at https://adoptium.net/en-GB/temurin/releases/)
3. An installation of PostgreSQL and an editing tool for it like pgAdmin 4
4. Software for making API requests (Postman, Insomnia, ...)

## Endpoints ##
The application currently has the following endpoints:
- Create (POST /api/products, Request Body contains the new object data)
- Get all (GET /api/products)
- Get by ID (GET /api/products/{id})
- Get by Name (GET /api/products/name/{name})
- Delete (DELETE /api/products/{id})
- Replace (PUT /api/products, Request Body contains the new object data)
