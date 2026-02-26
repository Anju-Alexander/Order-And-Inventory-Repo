# Inventory Service

The **Inventory Service** is a Spring Boot microservice responsible for managing product inventory batches with expiry dates.  
It supports querying inventory sorted by expiry date and updating inventory when orders are placed.

This service is part of the **Körber Java Microservices Assignment**.

---

## Responsibilities

- Maintain inventory batches for products
- Track quantity and expiry date per batch
- Return inventory sorted by earliest expiry
- Update inventory using FIFO (expiry-based) strategy
- Load schema and sample data automatically using Liquibase

---

## Tech Stack

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 In-Memory Database
- Liquibase (schema & data migrations)
- Maven
- JUnit 5 & Mockito

---

## How to Run

### Prerequisites
- Java 17 installed
- Maven installed

### Run the service
```bash
mvn spring-boot:run
