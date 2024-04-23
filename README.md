# Insurance Budget

This project consists in an Insurance Budget calculator API, which can calculate the price of an Insurance contract based
on the driver's age, accident history and car's accident history. This project features:

- Car management API
- Driver management API
- Accident management API
- Insurance Budget management API


## Tech stack

This project was build using the following technologies:

- Java 17
- Spring Boot
- JPA
- PostgreSQL
- Docker
- Swagger

Spring Boot is a powerful framework built upon JVM that lets us build robust applications with lower code lines.

JPA is a great tool for ORM that eases up the development proccess.

PostgreSQL is a reliable SGBD which is easily portable into component tests and docker containers.

Docker is an amazing containerization tool that helps us to standartize our runtime environment.

Swagger is a commonly used form of documenting Java APIs that help developers consume external APIs.

## Unit Tests

The project is covered by Unit Tests, that can be executed by running:

```shell
    ./gradlew test 
```

## Component (or Integration) Tests

This project is also covered with Component Tests, that can be executed by running:

```shell
    ./gradlew componentTest 
```

## Project Execution

Executing this project is as simple as having Docker installed and running:

```shell
    docker-compose up -d
```

This command will compile the code and create a container with the Database and the Spring Boot application up and running.

Both will be accessible via:

- PostgreSQL Database - localhost:5432
- Spring Boot application - localhost:8080

## API Docs

Once the project is running, the API docs can be accessed at:

http://localhost:8080/docs

There will be found all the endpoints regarding the API with 'Try it out' feature that Swagger provides us :)