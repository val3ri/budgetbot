# budgetbot

# Todo:

##### from 5.03:
- set up SwaggerUI 

##### from 23.03:
- created a new data model
- created new controllers

# current status:
Spring boot application working with PostgresSQL.
I have used this [tutorial](https://www.callicoder.com/spring-boot-jpa-hibernate-postgresql-restful-crud-api-example/ "Spring Boot, PostgreSQL, JPA, Hibernate RESTful CRUD API Example")

# data model
Entries:
 entryId,
 label,
 value,
 createdAt,
 userId
 
Users:
 id,
 username,
 email,
 password