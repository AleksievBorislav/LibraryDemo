# Library Management Demo App

This is a simple demonstration application designed to provide basic library management functionality. The primary focus of the project is to showcase the implementation of various technical features and patterns.

---

## Features

### **Non-Functional Features**
- **Spring Framework**
- **Java**
- **Global Exception Handling**
- **JDBC**: For database interaction, avoiding the additional abstraction layer of Hibernate.
- **Flyweight Pattern**: Used for migration.
- **Redis Cache**
- **Jobs**
- **CSV Generation**
- **Controller-Service-Repository Pattern**
- **Singleton Pattern**
- **Integration Testing**: Utilizes TestContainers for realistic database and Redis testing environments.
- **Docker**: Used exclusively for testing via TestContainers.
- **Swagger**
- **CI/CD Pipeline**: Automated pipeline that runs all tests on every push.

---

### **Functional Features**
This application supports the following basic interactions between entities:

- **Books**:
  - Add a book.
  - Delete a book.
  - Retrieve a book by its unique book number.
  - Get a list of books by title.
  - Check which books has a reader ever borrowed.
  - Retrieve the most popular books of the last week.
  
- **Readers**:
  - Add a reader.
  - Delete a reader.
  - Retrieve a reader by ID or EGN.
  - Get a list of unreturned books by EGN.

- **Borrowing**:
  - Borrow a book.
  - Return a book.
  - Automatically or manually generate overdue reports.

---

## Database Schema

Below is the database schema used by the application:

![DB Schema](https://github.com/user-attachments/assets/413de115-61e0-4640-8f2d-78803784e36c)


