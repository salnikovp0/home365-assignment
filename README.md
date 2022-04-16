# Home365 Management system for airlines
This is a demo application for a manage system of airlines

## Requirements
1. IntelliJ IDE
2. JDK 1.8 or above
3. Postman (if you use IntelliJ community edition)

## Instructions
1. Open IntelliJ IDE
2. Start new project from existing sources in `https://github.com/salnikovp0/home365-assignment`
3. Enable annotation processing (IntelliJ will hint you for that)   
4. Run `DemoApplication` Server

## Database
H2 in-memory DB. Initial content provided by `data.sql`, where `schema.sql` allows DB migrations and manipulations on the DB structure.

[H2 Console](http://localhost:8080/h2-console) provides DB console to the in memory DB,
>user name: sa
>
>password: password

## API
[SWAGGER](http://localhost:8080/swagger-ui/#/)
There are several APIs exposed by this application server, for your convenience they are gathered in `APIs.http` file.  
You can run all APIs directly from IntelliJ if you have ultimate version or use Postman instead.



