# Midas System
Project repository for the JPMC Advanced Software Engineering Forage program

## Project Details
**Company Name:** JPMorgan Chase & Co

**Program:** Advanced Software Engineering Project

**Project Name:** forage-midas

## About Project
the Midas system, a high-profile initiative responsible for processing financial transactions at scale.

In this program, I will focus on Midas Core — the service responsible for receiving, validating, and recording financial transactions.

 Midas Core relies on several external systems:

- Kafka to receive new transactions
- A SQL database to validate and store data
- A REST API to expose processed information

## 💻 Tech Stack & Tools

- **Language:** Java 17
- **IDE:** Eclipse (or) IntelliJ Community Edition
- **Build Tool:** Maven
- **Framework:** SpringBoot
- **Database:** Spring Data JPA
- **Message Queuing:** Kafka
- **API:** REST API
- **Testing:** JUnit

## Tasks
 ### Task-1: Project Setup & Running Test Cases

![Task1-comleted](./tasks/task1.png)

**What I Did!**

- I set up local development environment by installing Java 17, in Eclipse IDE.
- I exprore the existing project scafflod to understand how the midascore service is structured.
- I add the required dependencies to my spring Boot Project.
-  Build and run TaskOneTests. And Submitted the TaskOneTests output snippet.

### Task-2: Kafka Integration

![Task2-completed](./tasks/task2.png)

**What I Did!**

- I implemented a Kafka listener in Midas Core that reads from the topic defined in application.yml and deserializes each incoming message into the provided Transaction class.
- I run the TaskTwoTests, using debugger to inspect the first four received transactions, and I record the amounts attached to each.





