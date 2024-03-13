# Security JWT Rest API

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white) ![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white) ![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white) ![SpringSecurity](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=Spring-Security&logoColor=white) ![JWT](https://img.shields.io/badge/JWT-323330?style=for-the-badge&logo=json-web-tokens&logoColor=pink)


I developed a Rest API to manage products, providing CRUD (Create, Read, Update and Delete) operations. The API was built using **Spring Boot and Java, Mysql as the Database, Spring Security and JWT tokens for authentication control**.
This API enables the storage of product information, including product code, name, section, price, etc. On the user side, it allows the storage of usernames and passwords. Lastly, for roles, it only stores the name.

## Authentication
The API uses Spring Security for authentication control. The following roles are available be default:

```
USER  -> Standard user role only for basic product operations (get available products).
ADMIN -> Admin role for advanced product operations (registration, updates, or deletions).
```

It is possible for any user to have several roles at the same time, enabling them to work with both basic and advanced product operations.
To access protected endpoints, provide the appropriate authentication credentials in the request header. 

## Database Initialization with Default Data

For this project, default users and products have been created for quick testing by using the scheme.sql and data.sql files. However, you are also provided with endpoints to register new users, logging in, and subsequently storing new products.
The default users were created with the following credentials:

- Username: user1, Password: 123 (USER role only).
- Username: user2, Password: 123 (ADMIN role only).
- Username: user3, Password: 123 (both USER and ADMIN roles).

## API Endpoints
The API provides the following endpoints:

```markdown
POST   /auth/register
POST   /auth/login
DELETE /auth/delete/{id}

GET    /api/roles
GET    /api/roles/{id}

GET    /api/products
GET    /api/products/{id}
POST   /api/products
PUT    /api/products/{id}
DELETE /api/products/{id}
```

## Database Config 
For this API, the Mysql Database was used with the following configuration properties: 

- Database name: security_jwt_method1_db
- Username: root
- Password:

## Development Tools
This Rest API was built with:

- Spring Boot version: 3.2.3
- Java version: 17
