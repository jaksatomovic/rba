# RBA APP

Exercise application with client csv generating
Fetch them by:
- identification number (oib) (required fields)
Delete them by:
- identification number (oib) (required fields)
Create them by:
- firstName (required fields)
- lastName (required fields)
- identification number (oib) (required fields)

## API

In order to test application easy we have swagger-ui and postman collections 

| File                                                   | PATH                                                    |
|--------------------------------------------------------|---------------------------------------------------------|
| Application Collection                                 | [postman/RBA.postman_collection.json][PlApp]            |
| Swagger UI                                             | [swagger-ui](http://localhost:9017/rba/swagger-ui.html) |

## Tech

Technologies used while developing are

- Spring Boot running on Java 8
- Hibernate
- Jpa
- Postgres
- Liquibase
- SwaggerUI
- Git

And of course Application itself is open source with a [public repository][git-repo-url]
on GitLab.

## Installation

Application requires Postgres Database and Java 8 to run.

Enter the postgres shell and create database using following commands.

```sh
psql
create database rba_database;
create user user_rba with login encrypted password 'user_rba123';
grant all privileges on database rba_database to user_rba;
```

Application will take care of the rest once it is started. 
Just open app in preferred IDE and start it

## Development

Application is multimodule project which consists of
- rba-api
- rba-commons
- rba-web
- rba-core

All business logic is located inside rba-core module.
Idea behind structure inside core module is package-by-feature.

Each operation goes through series of steps:
1. Request validation - check if all required fields are set
2. Context creation - creation of context that contain all the needed data for next phases of the flow
3. Preconditions validation - each operation has a certain preconditions that need to be valid
4. Resource provisioning - changing the state of the resource
5. Response generation - generation of the response based on provided context

## TODO

- [x] Implement Table Mapping
- [x] Documentation
- [x] Increase Test Coverage



[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

[git-repo-url]: <https://github.com/jaksatomovic/rba>

[PlApp]: <https://github.com/jaksatomovic/rba/tree/main/postman>
