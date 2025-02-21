# Technical Decisions for leads-checker

## 1. Introduction

The purpose of this document is to describe the technical decisions made during the development of the leads-checker application.

## 2. Architecture

Based on the requirements outlined in the functional specification document, a microservices-based architecture has been implemented.
For project scaffolding, organization, and development, a hexagonal architecture has been applied, primarily following these principles:

- **Domain Driven Design (DDD)**: A rich domain has been defined, and domain entities, value objects, and domain services have been created.
- **Dependency Injection**: Dependency injection has been used for the instantiation of various application components.
- **SOLID**: The SOLID principles have been followed for the creation of application components.
- **Separation of Concerns (SoC)**: Business logic has been separated from presentation logic and persistence logic.

## 3. Technologies

Given personal experience and the requirements for the position, Java with Spring Boot has been chosen for developing a 
REST API. Maven has been used for dependency management and project build.

## 4. Testing

Mainly divided into two types:

- **Unit Tests**: Unit tests have been created for domain classes and domain services.
- **Integration Tests**: Integration tests have been created for the application's controller.

## 5. External Services to the Domain

As suggested in the functional specification document, external services are mocked internally to ensure the 
application's functionality. Given the architecture used for the project, the impact is minimal if the application were 
to be used in production in the future, as it would only require re-implementing external services and adapting them to 
the existing interfaces.

