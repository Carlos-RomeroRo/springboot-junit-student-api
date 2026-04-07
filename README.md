# 📘 springboot-junit-student-api

A Spring Boot REST API built to manage student data while practicing backend testing with **JUnit** and **Mockito**.  
This project focuses on clean architecture, maintainability, and solid testing practices.

---

## 📝 Purpose

This project was created as a hands-on learning exercise to strengthen backend development skills using **Spring Boot** and to build a solid foundation in automated testing.

The API revolves around a simple domain: **Student**, allowing focus on:

- Architecture
- Code organization
- Testing practices

---

## 🎯 Main Goals

- ✅ Build a REST API using Spring Boot
- ✅ Apply layered architecture (**Controller → Service → Repository**)
- ✅ Separate concerns using DTOs and mappers
- ✅ Work with JPA repositories and PostgreSQL
- ✅ Write unit tests using JUnit 5 and Mockito
- ✅ Prepare for integration testing with H2
- ✅ Maintain clean, scalable, and testable code

---

## ⚙️ Environment Variables Configuration

This project uses environment variables to manage sensitive data securely, following the **12-Factor App** methodology.

### 📌 Step 1: Create a `.env` file

Create a `.env` file in the **root directory** (same level as `pom.xml`) and add:

```env
# ==============================
# 🐘 PostgreSQL Configuration
# ==============================
POSTGRES_DB=studentsdb
POSTGRES_USER=your_user
POSTGRES_PASSWORD=your_password

# ==============================
# 🌱 Spring Boot Local Config
# ==============================
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/studentsdb
SPRING_DATASOURCE_USERNAME=your_user
SPRING_DATASOURCE_PASSWORD=your_password

# ==============================
# 🐳 Docker Configuration
# ==============================
# (Used when running with Docker Compose)
SPRING_DATASOURCE_DOCKER_URL=jdbc:postgresql://db:5432/studentsdb

# ==============================
# 🧪 Test Environment (H2)
# ==============================
TEST_DATASOURCE_USERNAME=sa
TEST_DATASOURCE_PASSWORD=
```

> ⚠️ **WARNING**  
> The `.env` file is included in `.gitignore`.  
> Never commit or share your credentials.

---

## 🚀 Tech Stack

| Technology        | Description                         |
|------------------|-------------------------------------|
| Java 17+         | Core programming language           |
| Spring Boot 3.x  | Backend framework                   |
| Spring Data JPA  | ORM & database interaction          |
| PostgreSQL       | Main database                       |
| H2               | In-memory database for testing      |
| JUnit 5          | Unit testing framework              |
| Mockito          | Mocking framework                   |
| Lombok           | Boilerplate reduction               |
| Maven            | Dependency management               |
| Docker           | Containerization                    |

---

## 📂 Project Structure

```bash
src/main/java/com/test/students
│
├── controller      # REST Controllers
├── dto             # Data Transfer Objects
├── entity          # JPA Entities
├── mapper          # Entity ↔ DTO Mappers
├── repository      # Data Access Layer
├── service         # Business Logic Interfaces
│   └── impl        # Service Implementations
│
└── StudentsApiApplication
```

---

## 🧱 Architecture Overview

```text
Client
  ↓
Controller
  ↓
Service
  ↓
Repository
  ↓
Database
```

✔ Clean separation of concerns  
✔ Easy to test and maintain  
✔ Scalable structure

---

## 🌐 API Documentation (Swagger)

Once the application is running, you can test all endpoints using Swagger UI:

👉 http://localhost:8081/swagger-ui/index.html#/student-controller/getAllStudent

From there you can:

- Test endpoints interactively
- View request/response schemas
- Validate your API behavior  

