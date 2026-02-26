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

# Order Service

---

## Overview

The **Order Service** is a Spring Boot microservice responsible for placing and managing customer orders.  
When an order is placed, the service communicates with the **Inventory Service** via REST APIs to reserve stock.

This service is designed following microservice best practices:
- Database-per-service
- Clear separation of concerns
- REST-based inter-service communication
- Automated database schema and data initialization

---

## Technology Stack

- **Java**: 17 (minimum supported: Java 8)
- **Framework**: Spring Boot
- **Build Tool**: Maven
- **Database**: H2 (in-memory)
- **ORM**: Spring Data JPA (Hibernate)
- **Database Migration**: Liquibase (YAML changelogs + CSV)
- **Testing**: JUnit 5, Mockito, Spring Boot Test
- **HTTP Client**: RestTemplate

---

## Project Setup Instructions

### Prerequisites
- Java 17 (or Java 8+)
- Maven 3.8+
- Inventory Service running locally (for full end-to-end flow)

### Build the Project
```bash
mvn clean install
- Maven installed

### Run the service
```bash
mvn spring-boot:run
