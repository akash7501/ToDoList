📝 TodoApp – Spring Boot + Docker + PostgreSQL






A Dockerized Spring Boot TodoApp connected to PostgreSQL, with automatic database and table creation.

🚀 Features

REST APIs for Users and Todo Lists

Dockerized PostgreSQL with auto-create

Auto table creation via Spring Boot JPA (ddl-auto: update)

Prebuilt Docker image available

Easy setup with Docker network

Sample JSON data for quick testing

🛠 Prerequisites

Docker Desktop

Java 17+
 (optional)

Optional: psql client

🌐 Step 1: Create Docker Network

⚡ Command:

docker network create mynetwork


This allows containers to communicate by name.

🐘 Step 2: Run PostgreSQL Container

⚡ Command:

docker run -d --name mypostgres --network mynetwork `
  -e POSTGRES_USER=postgres `
  -e POSTGRES_PASSWORD=root `
  -e POSTGRES_DB=DemoTesting `
  -p 5432:5432 `
  postgres:latest


🔹 Notes:

POSTGRES_DB=DemoTesting → database auto-created

mypostgres → container hostname

🏃‍♂️ Step 3: Run TodoApp Docker Image
Pull the image

⚡ Command:

docker pull akash7501/todoapp:latest

Run the container

⚡ Command:

docker run -d --name todoapp --network mynetwork -p 8080:8080 akash7501/todoapp:latest


🔹 Notes:

App accessible at http://localhost:8080

Connects automatically to the PostgreSQL container

⚙ Spring Boot Datasource Configuration

application.yml:

spring:
  datasource:
    url: jdbc:postgresql://mypostgres:5432/DemoTesting
    username: postgres
    password: root
    driver-class-name: org.postgresql.Driver

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

📡 API Endpoints
Users
Method	Endpoint	Description
GET	/api/users	Get all users
GET	/api/users/{id}	Get user by ID
POST	/api/users	Create new user
PUT	/api/users/{id}	Update user
DELETE	/api/users/{id}	Delete user

⚡ Example JSON to create a user with todo lists:

{
  "username": "akash",
  "email": "akash@example.com",
  "password": "1234",
  "todoLists": [
    {
      "name": "Daily Tasks",
      "tasks": [
        {
          "title": "Buy groceries",
          "description": "Milk, Bread, Eggs",
          "dueDate": "2025-09-30",
          "status": "PENDING"
        },
        {
          "title": "Finish Spring Boot Project",
          "description": "Complete backend for ToDo app",
          "dueDate": "2025-10-05",
          "status": "PENDING"
        }
      ]
    }
  ]
}

Todo Lists
Method	Endpoint	Description
GET	/api/users/{userId}/lists	Get all todo lists for a user
POST	/api/users/{userId}/lists	Create a new todo list for a user

⚡ Example JSON for creating a todo list:

{
  "name": "Weekend Tasks",
  "tasks": [
    {
      "title": "Clean house",
      "description": "Living room and kitchen",
      "dueDate": "2025-10-02",
      "status": "PENDING"
    }
  ]
}

🔎 Step 5: Verify Setup

⚡ Commands:

docker ps                   # List running containers
docker logs -f todoapp      # View app logs
psql -h localhost -p 5432 -U postgres -d DemoTesting  # Optional: connect to DB

🔄 Step 6: Reset Database

⚡ Commands:

docker stop mypostgres
docker rm mypostgres
docker run -d --name mypostgres --network mynetwork `
  -e POSTGRES_USER=postgres `
  -e POSTGRES_PASSWORD=root `
  -e POSTGRES_DB=DemoTesting `
  -p 5432:5432 `
  postgres:latest


Spring Boot will recreate tables automatically.

💡 Notes

Containers must be on the same Docker network (mynetwork)

Database auto-created via Docker env variable

Tables auto-created via Spring Boot JPA

Prebuilt image allows running without building from source
