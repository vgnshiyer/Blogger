# Blogger

Blogger is a fullstack blogging application that is built with Spring Boot and React JS. It has an API that is documented with Swagger and it also implements JWT authentication.

## Features
-   Create, read, update, and delete blog posts
-   Comment on blog posts
-   Authenticate users with JWT tokens
-   Search for blog posts
-   Filter blog posts by category

**Getting Started**

To get started with Blogger, you will need to have the following installed:
-   Java 8
-   Maven
-   Node JS
-   npm

Once you have all of the required dependencies installed, you can clone the repository from GitHub:
https://github.com/vgnshiyer/Blogging-Application.git

Build and run the backend using the included maven wrapper
```
./Backend/blog-app-apis/mvnw spring-boot:run
```
Build and run the frontend using npm
```
cd Frontend/blog-app/ && npm start
```
Alternatively, you can also start the frontend using docker-compose
```
cd Frontend && docker-compose up
```
Open [http://localhost:3000](http://localhost:3000) to view it in your browser.

## Architecture

The following diagram shows the architecture of the Blogger application.

![image](https://github.com/vgnshiyer/Blogging-Application/assets/39982819/ee8ed12a-4962-4814-ab5f-d7c2d5eda787)

The application is divided into two main components: the backend and the frontend. The backend is responsible for handling all of the business logic and data access. The frontend is responsible for rendering the user interface and interacting with the backend.

The backend is implemented with Spring Boot. Spring Boot is a popular framework for building Java applications. It provides a lot of features that make it easy to build and deploy Java applications.

The frontend is implemented with React JS. React JS is a popular framework for building user interfaces. It is known for its performance and its ability to be easily reused.

The two components are connected using a REST API. The REST API is documented with Swagger. Swagger is a popular tool for documenting REST APIs. It makes it easy for developers to understand how to use the API.

