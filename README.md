# comp3

This is simple application to manage invoices.

App has exposed 3 endpoints:
- for creating invoice
- for receiving all invoices
- for receiving single invoice (searched by invoice id)

Stack used in this task:
- Java 11
- Spring Boot (JPA, WEB, Security)
- Swagger
- ehcache



## Launching the app
To run this app use docker by command:
``` 
docker-compose build && docker-compose up
```
You can also do it manually just running tha app by jar or IntelliJ.</br>
Then database can be launched also by docker:
```
docker run --name mysql -e MYSQL_ROOT_PASSWORD=password -p 3306:3306 -d mysql:latest
```
