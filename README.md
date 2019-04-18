# Ateknea Backend Test

Execute tests:

    mvn test

Run this project:

At the moment it is not possible to run the project with the command

    mvn spring-boot:run

It looks like some fixes must to be done on pom configurations,
but by now the project can be executed from Intellij IDEA as a
Spring Boot project pointing to the main class, which is

    com.ateknea.BootApplication


>About this project:

This is a Spring Boot project which tries to follow the DDD principles.
It has been structured in the following 5 layers:

- Domain - this module contains the business related Entities, Value Objects
and logic. It does not depend on any other module.

- Infrastructure - it consists of everything that exists independently of our 
application (libraries, database, etc). It only depends on domain layer.

- Presentation - this module has the responsibility to format and dispense the
information to/from the user. It depends on both Domain and Application layers.

- Application - it contains the application logic and controls the flow of the
program. It depends on Domain and Infrastructure.
