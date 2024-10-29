# Spring Boot Role-Based Access Control

This project demonstrates how to implement role-based access control in a Spring Boot application using Spring Security.
We set up different roles to restrict access to specific endpoints, ensuring that only authorised users can interact
with restricted sections of the application.

## Features

- **Spring Security Integration**: Secure application endpoints with ease.
- **Role-Based Access Control**: Define roles such as authenticated user, authorised user, and admin.
- **Automated Testing**: Use mock users with predefined roles to validate access restrictions.
- **In-Memory User Storage**: Quick setup for roles and access control without the need for a database.

## Endpoints

This project includes various endpoints, each with its own access requirements based on user roles:

- **Public Route (`/`)**: Accessible to all users.
- **Authenticated Route (`/only-authenticated`)**: Restricted to authenticated users, regardless of role.
- **Authorised Route (`/only-authorised`)**: Limited to users with the "AUTHORiSED" role or admin access.
- **Admin Route (`/admin`)**: Exclusive to users with the "ADMIN" role.

## Project Structure

- **Controllers**: Handle the different endpoint requests.
- **Configuration**: Contains the Spring Security configuration for role-based access control.
- **Test Suite**: Comprehensive automated tests to verify access restrictions using Spring Security's `@WithMockUser`.

## Getting Started

### Prerequisites

- **Java 21**: Make sure you have Java 21 installed.
- **Maven**: Used for dependency management.

### Project Setup

1. Clone this repository:

```bash
   git clone https://github.com/your-username/spring-security-roles-exploration.git
```

2. Navigate to the project directory:

```bash
cd spring-security-roles-exploration
```

3. Build the project:

```bash
mvn clean install
```

4. Run the application:

```bash
mvn spring-boot:run
```

## Dependencies

The project includes the following Spring Boot dependencies:

- `spring-boot-starter-security`
- `spring-boot-starter-web`
- `spring-boot-devtools`
- `spring-boot-starter-test`
- `spring-security-test`

## Accessing Endpoints

To test the endpoints, use the credentials and roles defined in `SpringSecurityConfiguration`:

- **Authenticated User**:
    - Username: `authenticated`
    - Password: `authenticated`

- **Authorised User**:
    - Username: `authorised`
    - Password: `authorised`
    - Role: `AUTHORISED`

- **Admin User**:
    - Username: `admin`
    - Password: `admin`
    - Role: `ADMIN`

## Testing the Project

Run the automated tests with:

```bash
mvn test
```

The test suite validates each endpoint's access restrictions using `@WithMockUser` to simulate various roles.

## HTTP Scratches

```
###
GET http://localhost:8080/

###
GET http://localhost:8080/anything

###
GET http://localhost:8080/only-authenticated

###
GET http://localhost:8080/only-authorised

###
GET http://localhost:8080/admin

###
GET http://localhost:8080/only-authenticated
Authorization: Basic authenticated authenticated

###
GET http://localhost:8080/only-authenticated
Authorization: Basic authorised authorised

###
GET http://localhost:8080/only-authenticated
Authorization: Basic admin admin

###
GET http://localhost:8080/only-authorised
Authorization: Basic authenticated authenticated

###
GET http://localhost:8080/only-authorised
Authorization: Basic authorised authorised

###
GET http://localhost:8080/only-authorised
Authorization: Basic admin admin


###
GET http://localhost:8080/admin
Authorization: Basic authenticated authenticated

###
GET http://localhost:8080/admin
Authorization: Basic authorised authorised

###
GET http://localhost:8080/admin
Authorization: Basic admin admin
```

## Further Reading

This project builds on previous posts covering Spring Security basics and advanced configurations. For a deeper
understanding, check out these blog posts:

* https://codereviewvideos.com/first-steps-with-spring-security-amp-rest-api/
* https://codereviewvideos.com/form-based-login-in-spring-boot-with-spring-security/