# mongo-bulk-operations-test

Compare the performance of single updates and bulk update operation.

**Insert dummy records**

```
curl -X POST http://localhost:8080/books
```

**Delete all records**

```
curl -X DELETE http://localhost:8080/books
```

**Bulk update**

```
curl -X PUT http://localhost:8080/books/bulk
```

**Single updates**

```
curl -X PUT http://localhost:8080/books/single
```
