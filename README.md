# Products API
The Products API is a Spring Boot-based RESTful service for inventory management, featuring role-based user authentication.
- **Architecture**: Implements a service-layer architecture with a repository pattern
- **Testability**: Layer separation enhances testability
- **Maintainability**: A clean and structured codebase for easy management

## Technologies Stack
- Java 21
- Spring Framework
  - Spring Boot, Spring Data MongoDB, Spring Web, Spring Security, Bean Validation, Lombok
- JUnit and Mockito
- MapStructs
- Log4j2
- MongoDB
- Docker

## Build and Run
Follow these steps to build and run the application:

```shell
# Open a terminal (Command Prompt or PowerShell for Windows, Terminal for macOS or Linux)

# Ensure Git is installed
# Visit https://git-scm.com to download and install console Git if not already installed

# Clone the repository
git clone https://github.com/vitor-reck/product-api.git

# Navigate to the Docker-compose directory
cd product-api/docker-compose

# Check if Docker and Docker-compose are installed
docker -v  # Check the Docker version
docker-compose -v  # Check Docker-compose version
# Visit the official Docker website to install or update it if necessary

# Start the Services
docker-compose up -d  # Starts the Products API and MongoDB

# Managing Containers
docker-compose start  # Start stopped containers 
docker-compose stop   # Stop running containers 
docker-compose down --volumes   # Stop and remove containers, network, and MongoDB volume  
```

## Preloaded Users and Categories
User and category data are automatically seeded via the <em>mongo-init.js</em> script.
```shell
# Open a terminal (Command Prompt or PowerShell for Windows, Terminal for macOS or Linux)

# List running containers
docker ps

# Access the MongoDB container
docker exec -it *Container ID* bash

# Open the MongoDB shell
mongosh

# Retrieve data
db.users.find()  # View all users
db.categories.find()  # View all categories 

# Exit MongoDB and the container
exit  # Run twice to exit both MongoDB and the container
```

## API Endpoints (Postman Collection)
The following endpoints can be tested using Postman
| Action               | HTTP Method | Resource           | Roles       |
|----------------------|-------------|--------------------|-------------|
| Authentication       | POST        | /api/auth          | None        |
| List products        | GET         | /api/products      | ADMIN, USER |
| Get product by id    | GET         | /api/products/{id} | ADMIN, USER |
| Create product       | POST        | /api/products      | ADMIN       |
| Update product by id | PUT         | /api/products/{id} | ADMIN       |
| Delete product by id | DELETE      | /api/products/{id} | ADMIN       |
| List categories      | GET         | /api/categories    | ADMIN, USER |
