# Bookspace

This project is New Aim Product Management application. It is developed using Java, SQL, ES, and Maven.

## Prerequisites

- Java 21
- Maven 3.6.3
- MySQL 8.0+
- Nacos
- Redis
- Elasticsearch 8.12.2

## Getting Started

First, clone the repository to your local machine:

```bash
git clone https://github.com/JunCheung/new-aim-cloud
```

## Initial

Initial database with the files in the folder "sql" in the project.

## Before running and deploying

Make sure you have changed the configuration for Mysql/Redis/Nacos/ES.

## Building the Project

To build the project, run the following command:

```bash
mvn clean install package '-Dmaven.test.skip=true'
```

## Running the Project

To run the project, use the following command:

Run gateway

```bash
cd bookspace-gateway
mvn spring-boot:run
```

Run book module

```bash
cd bookspace-module-book/bookspace-module-book-biz
mvn spring-boot:run
```

## Deploying the Project With Docker

The profile "dev" is default, 

```bash
cd deploy
docker-compose up --build -d
```
