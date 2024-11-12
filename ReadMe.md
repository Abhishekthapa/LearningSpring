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