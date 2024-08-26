# About #
This is a Spring Boot project which offers basic CRUD functionality for accessing a PostgreSQL database containing "products".
It uses the JPA and PostgreSQL libraries to make an automatic connection to the database. If the table does not exist locally then the app will create a temporary one automatically.
*Important:* the database saves prices using the smallest currency unit (i.e. cents). If a product costs 1€ then the price should be saved as 100. 
Because of this, prices are always stored as integers and only become actual prices when the entity is converted to a DTO.
This follows best practices for storing price info and avoids problems with math and rounding (see: https://cs-syd.eu/posts/2022-08-22-how-to-deal-with-money-in-software)
Libraries such as https://javamoney.github.io/ri.html could be added to the project in the future to add easy currency conversion support. 

# User Guide #
In order to run the project you will need:
1. A Java compiling software such as IntelliJ or Eclipse
2. JDK installation of at least Java 21 LTS (can be downloaded at https://adoptium.net/en-GB/temurin/releases/)
3. An installation of PostgreSQL and an editing tool for it like pgAdmin 4
4. Software for making API requests (Postman, Insomnia, ...)

## Database setup ##

Start by creating a new database server using pgAdmin. Make sure to remember the username and password for this database.
Included in the repository is an .sql file that contains the SQL statement for the creation of the Products table. You can execute this using pgAdmin to create a permanent database on your PC. 

When all of this is prepared you can begin setting up the project by downloading the code from this repository.
Once you have downloaded it and extracted it to any folder, you can now open it in the editor of your choice. 

Open the application.properties file and edit the following lines with your information (replace the values within the {} brackets):
spring.datasource.url=jdbc:postgresql://localhost:{Port, default: 5432}/{DB Name}
spring.datasource.username= {DB username}
spring.datasource.password= {DB password}

After you have filled out this information you can start the project (Shift + F10). If the project starts without errors you can begin testing it by adding some values to the database using the endpoints detailed below.
Example JSON for adding a product via POST request:

    {
        "name": "Casio F91W",
        "description": "A simple and elegant digital watch.",
        "price": 1998,
        "currency": "EUR",
    }

## Endpoints ##
The application currently has the following endpoints:
- Create (POST /api/products, Request Body contains the new object data)
- Get all (GET /api/products)
- Get by ID (GET /api/products/{id})
- Get by Name (GET /api/products/name/{name})
- Delete (DELETE /api/products/{id})
- Replace (PUT /api/products, Request Body contains the new object data)
