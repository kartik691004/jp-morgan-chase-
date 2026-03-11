# JPMC Midas Core - Advanced Software Engineering Simulation

This repository contains the `midas-core` project developed as part of the JPMorgan Chase & Co. Advanced Software Engineering Forage program.

## Overview

Midas Core is a Spring Boot application that processes transactions via Kafka, integrates with an external Incentive API, records transaction logic to an H2 in-memory database, and exposes a RESTful API to check user balances. 

## Features

- **Kafka Consumer**: Listens to real-time transaction streams on a designated Kafka topic (`midas-transactions`).
- **External API Integration**: Communicates with the external `IncentiveService` API to retrieve incentive amounts for each valid transaction.
- **Database Persistence**: Utilizes Spring Data JPA to store user information and validated transaction records in an in-memory H2 database.
- **RESTful API Endpoint**: Exposes a GET endpoint `/balance` to fetch a user's current account balance by calculating the net of their transaction history and incentives.
- **Comprehensive Testing**: Includes automated unit and integration tests written using Spring Boot Test and Testcontainers to spin up ephemeral Kafka brokers and databases for robust environment isolation.

## Technology Stack

- **Java 17**
- **Spring Boot 3.2.5** (Web, Data JPA)
- **Spring Kafka** for event-driven processing
- **H2 Database** for fast, reliable in-memory persistence
- **JUnit 5 / Testcontainers** for isolated integration testing
- **Maven** as the build automation tool

## How to Run

1. **Clone the repository**:
   ```bash
   git clone <your-repo-url>
   cd forage-midas
   ```

2. **Run the application**:
   Use the Maven wrapper to build and start the Spring Boot application.
   ```bash
   ./mvnw spring-boot:run
   ```
   *(On Windows, use `.\mvnw.cmd spring-boot:run`)*

3. **Run the Tests**:
   Execute the integration tests to verify processing logic and Kafka consumer setup:
   ```bash
   ./mvnw test
   ```
   *(On Windows, use `.\mvnw.cmd test`)*

## Core Components

- `TransactionListener`: Subscribes to transaction topics and orchestrates the transaction validation and processing pipeline.
- `DatabaseConduit`: Manages persistence logic for transactions and users, acting as an abstraction over JPA repositories.
- `IncentiveService`: Invokes the external incentive API to fetch dynamic values based on transaction history.
- `BalanceController`: Exposes the local HTTP endpoint (`http://localhost:33400/balance?userId=...`) returning a user's final balance.
