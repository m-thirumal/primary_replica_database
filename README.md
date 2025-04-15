# primary_replica_database
Read-write and read-only transaction routing to primary and replica PostgreSQL database with Spring boot
https://spring.io/blog/2007/01/23/dynamic-datasource-routing/

![Brainstrom](primary_replica_transaction.png)

## Routing

[http://localhost:8080/order/create](http://localhost:8080/order/create) will create new order/insert new row to `Primary` database

[http://localhost:8080/order](http://localhost:8080/order) will fetch record from `replica` database



