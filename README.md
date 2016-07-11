## Synopsis

This is a simple web service with simple CRUD functions running on Spring boot.

Run mvn package then mvn spring-boot:run

## Code Example

Get Customers
```
[GET]  http://localhost:8080/customer/all
```

Get specific customers by last name
```
[GET] http://localhost:8080/customer/lastname/<last name>
```
Save customer

```
[POST] http://localhost:8080/customer/save

{
   "lastName": "Zabala",
    "firstName": "Allan"
}

```
Update customer

```
[PUT] http://localhost:8080/customer/update

{
    "id": 2,
    "lastName": "Andrews",
    "firstName": "Francis"
}

```

Delete customer by id

```
[DELETE] http://localhost:8080/customer/delete/<id>

```
