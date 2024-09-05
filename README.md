# Document and Author Management Web Application

This application manages documents and authors, allowing users to perform CRUD operations efficiently. Each document has a title, body, authors, and references, while authors have a first name and a last name.

## Features

- **Author Management**: Add, delete, edit, and view authors.
- **Document Management**: Add, delete, edit, and view documents.
- **Data Storage**: Uses an in-memory H2 database.
- **Error Handling**: Provides meaningful feedback for user errors.
- **Validation**: Ensures input data integrity and security.
- **API Documentation**: Uses Swagger for clear and consistent API endpoints ([Swagger URL](http://localhost:8080/swagger-ui/index.html)).

## Tech Stack

- **Build Tool**: Maven
- **Backend**: Spring Boot, Spring Data JPA
- **Database**: H2 (in-memory SQL database)
- **Testing**: JUnit for unit tests
- **API Documentation**: Swagger

## Workflow


1. **Clone the Repository**: Clone the repository to your local machine.
2. **Build the Project**: Use Maven to build the project.
   ```bash
   mvn clean install
1. **Run the Application**: The application starts with an embedded H2 database.
   ```bash
   mvn spring-boot:run
2. **API Interaction**: Use RESTful endpoints to manage authors and documents.
3. **Testing**: Unit tests ensure the correctness of core functionalities.

## Future Improvements

- Integration tests for end-to-end validation.
- Transition to a more robust SQL database (e.g., PostgreSQL).
- Implement Kafka for messaging and asynchronous processing.
- Introduce caching for performance improvements.
- Develop more sophisticated authentication mechanisms.

---
