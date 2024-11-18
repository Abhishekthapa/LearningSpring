# Prerequisites

* Docker/Postgres SQL
* Maven
* JAVA


# Day 1: Shree Ganesh

* Initialized the project from start.spring.io
* Made jar using mvn clean package -DskipTests
* Ran the default class


# Day 2: Embedding Database

* Create data.sql and schema.sql under src/main/resources. These files will be automatically read by Springboot while running.
* Add jpa and h2 dependencies in the project for data access and embedded H2 database for storing and retrieving data.
* Adjust application.properties to set logging to debug so that we cna review log and set hibernate auto schema to none. Doing so, we can create our own schema and database via data and schema.sql.
* Created Spring data repo by definining entity class **Room** and an interface **RoomRepository** that extends **CrudRepository**
* Mapped the entity to the database table and demonstrated repository method for database interactions.
* Created more entities (Reservation and Guest). Added interfaces to it for CRUD operation.
* Created a method in reservation repo interface to extract reservation with reservation date = provided date.
* Understood IoC in detail.

# Day 3: IoC, Dependency Injection, and Service Abstraction

* Created a @Service component which generates room reservation based on date provided.
* Injected various beans via constructor instead of directly autoWiring it.

# Day 4: MVC Pattern, Spring Controller

* Controller handles web requests, view the model and assemble the data for the model using various business logic.
* Spring controller is a spring bean which has web request handling methods.
* When returning a string from a controller, this string is the name of template file without extension which thymeleaf will use to generate HTML. Eg: roomres is returned so thymeleaf will search for roomres.html file to render the view.
* 