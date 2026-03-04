# Server App — job-grpc-service

# job-grpc-service – gRPC Microservice

job-grpc-service is a **Spring Boot gRPC microservice** responsible for handling job portal business logic and database operations.

The service exposes **gRPC endpoints defined using Protocol Buffers**, enabling efficient service-to-service communication using **HTTP/2 and binary serialization**.

---

## Architecture

````

REST Gateway (JobApp)
│
▼
gRPC Client
│
▼
job-grpc-service
Spring Boot + gRPC
Port: **9091**
│
▼
Service Layer
│
▼
Repository Layer
│
▼
PostgreSQL Database

````

---

## Features

- gRPC server implementation
- Protocol Buffers contract
- CRUD operations for Job Posts
- Job search functionality
- Spring Data JPA integration
- PostgreSQL database
- High-performance service communication

---

## Tech Stack

- Java
- Spring Boot
- gRPC
- Protocol Buffers
- Spring Data JPA
- PostgreSQL
- Gradle
- Lombok

---

## gRPC Services

The following RPC methods are implemented:

```
GetJob
GetAllJobs
AddJob
UpdateJob
DeleteJob
SearchJobs
```

---

## Proto Definition Example

```proto
syntax = "proto3";

service JobService {

  rpc GetJob (JobRequest) returns (JobResponse);

  rpc GetAllJobs (Empty) returns (JobList);

  rpc AddJob (JobResponse) returns (JobResponse);

  rpc UpdateJob (JobResponse) returns (JobResponse);

  rpc DeleteJob (JobRequest) returns (DeleteResponse);

  rpc SearchJobs (SearchRequest) returns (JobList);
}
```

---

## Database Configuration

Example PostgreSQL configuration:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/demoDB
spring.datasource.username=postgres
spring.datasource.password=1234
spring.jpa.hibernate.ddl-auto=update
```

---

## Run the Service

```bash
./gradlew bootRun
```

The gRPC server will start on:

```
grpc.server.port=9091
```

---

## Why gRPC?

Compared to REST APIs:

| Feature | REST | gRPC |
|------|------|------|
| Protocol | HTTP/1.1 | HTTP/2 |
| Data Format | JSON | Binary (Protobuf) |
| Performance | Moderate | High |
| Streaming | Limited | Built-in |

gRPC is ideal for **internal service-to-service communication in microservices**.

---

## Purpose

This project demonstrates how to build a **high-performance backend microservice using gRPC, Protocol Buffers, and Spring Boot**.
