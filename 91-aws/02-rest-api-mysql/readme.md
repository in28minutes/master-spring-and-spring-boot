# Todo and Hello World Rest APIs with My SQL

Run com.in28minutes.rest.webservices.restfulwebservices.RestfulWebServicesApplication as a Java Application.

### Launch MySQL as Docker Container

```
docker run --detach --env MYSQL_ROOT_PASSWORD=dummypassword --env MYSQL_USER=social-media-user --env MYSQL_PASSWORD=dummypassword --env MYSQL_DATABASE=social-media-database --name mysql --publish 3306:3306 mysql:8-oracle
```

### mysqlsh commands
```
mysqlsh
\connect social-media-user@localhost:3306
\sql
use social-media-database
select * from user_details;
select * from post;
\quit
```



## Hello World Resource

- http://localhost:5000/hello-world

```txt
Hello World
```

## Users Resource

POST http://localhost:5000/jpa/users

```json
{
    "name": "Ranga",
    "birthDate": "2000-07-19"
}
```

GET http://localhost:5000/jpa/users

```json
[
    {
        "id": 1,
        "name": "Ranga",
        "birthDate": "2000-07-19"
    }
]
```

GET http://localhost:5000/jpa/users/1

```json
{
    "id": 1,
    "name": "Ranga",
    "birthDate": "2000-07-19"
}
```
POST to http://localhost:5000/jpa/users/1/posts

```
{
	"description":"I want to get AWS Certified"
}
```

GET http://localhost:5000/jpa/users/1/posts

```
[
    {
        "id": 1,
        "description": "I want to get AWS Certified"
    }
]

```