# Blogger-API
A core Backend Project which allows users to post, update, delete, comment on blogs. 

Requirements:

* User should be able to create, update, delete and post blogs.
* User should be able to add, update, delete comments on posts.
* Categorize the blogs based on the genre.
* New user should be able to register on our application.
* User should be able to login.
* User should also be able to upload a picture along with the post.

# API Documentation

## Blogging Application API

This application allows users to create/delete/update/comment on blog posts.

### API Endpoints available:

* UserController
* PostController
* Login
* CategoryController

### Collections
-----------------------------------
### CategoryApi

This Endpoint Manages activities related to Post Categories. It provides below requests:

* **POST** - create category

/api/categories/

This request allows creating new post categories.

sample body:
```
{
  "categoryTitle": "Data Structures",
  "categoryDescription": "We make life easier!"
}

```
* **GET** - get category

/api/categories/

This request allows retrieving categories.

* **PUT** - update category

/api/categories/{id}

This request allows updating categories.

sample body:
```
{
  "categoryTitle": "Data Structures",
  "categoryDescription": "We make life easier!"
}
```

* **DELETE** - delete category

/api/categories/{id}

This request allows deleting categories.

---------------------------------------------
### Login

This endpoint manages user authentication.

**Requests:**

* **POST** - Login User

/api/auth/login

This request allows user to authenticate.

sample body:
```
{
  "username": "rohangore@gmail.com",
  "password": "rohan123"
}
```

sample response:
```
{
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2Z25zaGl5ZXJAZ21haWwuY29tIiwiZXhwIjoxNjY4Nzg2NTE3LCJpYXQiOjE2Njg3Njg1MTd9.3JacMMqhg7vjC9EfYOLKggNxiBvm9KPLZo7OfQYdMSv7gqbV_BuUwJml4_5c2yK4vJYBv4KrlUzNf4nEcoCUaw"
}
```
------------------------

### UserApi

This API allows to create, update, delete users.

**Requests:**

* **POST** - add user

/api/users/

sample body:
```
{
  "name": "user",
  "email": "user@gmail.com",
  "password": "user123",
  "about": "I am a Frontend Engineer"
}
```

* **GET** - get user

/api/users/

/** Pass "Bearer {api token}" **/

sample response
```
[
    {
        "id": 1,
        "name": "Vignesh Iyer",
        "password": "$2a$10$NPZh.019jxhqMtFe6tBWje5HMBoDdmcKdpDSzYeBzkh409Y0K49dC",
        "email": "vgnshiyer@gmail.com",
        "about": "I am a Software Engineer",
        "roles": [
            {
                "name": "ROLE_ADMIN",
                "id": 501
            }
        ]
    },
    {
        "id": 7,
        "name": "User",
        "password": "$2a$10$zrzu94K5MkZAJefzd1A0tOLtCVBFTALOZpU/8pIt3eNtjkD49KaZa",
        "email": "User@gmail.com",
        "about": "I am a Frontend Engineer",
        "roles": [
            {
                "name": "ROLE_GENERAL",
                "id": 502
            }
        ]
    }
    ...
```

* **UPDATE** - update user

/api/users/{id}

* **DELETE** - delete user

/api/users/{id}

* **POST** - register user

/api/auth/register

sample body:
```
{
    "name" : "newUser",
    "password" : "abcdef",
    "email" : "newUser@gmail.com",
    "about" : "I am a System Engineer"
}
```
--------------------------------

### PostApi

This API allows users to create/update/delete blog posts.

**Requests:**

* **POST** - create post

/api/user/{userid}/category/{categoryid}/posts

sample body:
```
{
    "title" : "Dijkstra's Shortest Path Algorithm",
    "content" : "You will be there and you won't even know it :)"
}
```

* **UPDATE** - update post

/api/posts/{postid}

* **GET** - get post

/api/posts?pageNum=0&pageSize=3&sortBy=title&sortDir=asc&Authorization=Bearer 

sample response:
```
{
    "content": [
        {
            "postId": 1,
            "title": "C++ programming tricks",
            "content": "very awesome c++ tricks you would never use :)",
            "imageName": "default.png",
            "date": 1666705876095,
            "category": {
                "categoryId": 1,
                "categoryTitle": "Data Structures",
                "categoryDescription": "We make life easier!"
            },
            "user": {
                "id": 1,
                "name": "Vignesh Iyer",
                "password": "$2a$10$NPZh.019jxhqMtFe6tBWje5HMBoDdmcKdpDSzYeBzkh409Y0K49dC",
                "email": "vgnshiyer@gmail.com",
                "about": "I am a Software Engineer",
                "roles": [
                    {
                        "name": "ROLE_ADMIN",
                        "id": 501
                    }
                ]
            },
            "comments": [
                {
                    "content": "Wow!! This is awesome. Thanks for sharing!!",
                    "userId": 7,
                    "id": "3"
                },
                ...
```

* **GET** - search post with keyword

/api/posts/search/?keyword=python

* **GET** - get posts from a user

/api/user/{userid}/posts

* **GET** - get posts by category

/api/category/{categoryid}/posts

* **DELETE** - delete post

/api/posts/{postid}

* **POST** - upload image

/api/posts/image/upload/{postid}

form-data:

image : "image_path.jpeg"

* **GET** - download image

/api/posts/image/d162bf82-ab56-4f7e-866a-2712c9bbd244.png

* **POST** - add comment

/api/posts/{postid}/comment/create/user/{userid}

sample body:
```
{
    "content" : "Thanks for sharing!"
}
```

* **DELETE** - delete comment

/api/posts/comment/{commentid}/delete

-------------------------------------------------
