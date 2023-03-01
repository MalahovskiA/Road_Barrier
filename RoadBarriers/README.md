# project Barrier
This project contains:
`Objects`
`Methods`
`Collections`
`Saving and reading data to/from files (multiple files)`
`Working with the database (Hibernate, SpringData)`
`Stream and Optional`
`Multithreading`
`Web technologies and Spring (RestController, Service, Repository)`
Project used `Spring Boot`, `Spring Data JPA` and `Postgres` to store data, `Spring Security 5` to implement security issues, 
`JWT` to communicate between `Server` and `Client`.
The project is hosted on GitHub

The project also demonstrated work with:
`Working with strings`
`Interfaces`
`Generic types`
`Lambda Expressions`


# Running
To run the application enter the command line: `mvn spring-boot:run`

The project provides for the calculation of road barrier structures based on safety conditions TP TC 014

The project provides the following roles for users:

```
'ROLE_USER';
'ROLE_MANAGER';
'ROLE_ADMIN';
```

# Signup
`POST localhost:8081/api/auth/signup`
```
{
    "username": "admin",
    "email": "admin@gmail.com",
    "password": "12345678",
    "role": ["admin, moderator"]
}
```

# Get JWT Token
`POST localhost:8081/api/auth/signin`
```
{
    "username": "admin",
    "password": "12345678"
}
```

# Make Request to Secure API
`localhost:8081/api/test/admin`

```Bearer {jwt}```

# Make Request to Public API
`localhost:8081/api/test/all`
