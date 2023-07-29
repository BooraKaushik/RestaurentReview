# Restaurant Review

## Introduction

Restaurent Review Repo is a Backend Application Built using technologies like Spring Boot, Hibernate, MySQL, JWT. This Application provides APIs so that users can comment and rate restaurants. This application allows users to create a profile, Restaurants to create a restaurant, food and menu entities. The users can comment on these restaurants. This application provides Authentication using JWT. Thereby to create a restaurant, comment a user must be authenticated and must include bearer token in header.

## How to Run the Application
To run the Application follow the following Steps,
1. Clone the Repository and change the current directory path to the cloned repository path.
2. Make sure a DB instance is running with username password mentioned in the Application.properties.(Any changes in Application.properties require re building the Application)
3. Move into the target directory and run the following command "java -jar RestaurentReview-0.0.1-SNAPSHOT.jar". 
4. This should bootup the application and the application starts running on port 8080.

## Postman Collection
The Postman Collection consists of all the sample API requests to test the endpoints,
Automated Login Process in postman. To login an automted script creates a temporary user and hits the login API to obtain a JWT. This JWT inturn is used by all the other requests to set the Bearer Token. 

## API Documentation

### User Entity

Creating User doesnt need authentication. So no JWT has to be included for creating a user.

| HTTP Method |         Path          | Description                                                  | Example Body                                                                                                                                                                                                                                                                                                                                                                                                                                               | Requires Bearer Token |
| :---------: | :-------------------: | :----------------------------------------------------------- | :--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | :-------------------: |
|    POST     |     /api/v1/user/     | Adds user to DB. Expects the user to be present in the body. | <pre>{  <br>    "userName":"Kaushik",<br>    "password":"Test",<br>    "firstName":"Kaushik",<br>    "lastName":"Boora",<br>    "dob":"1998-12-25",<br>    "gender": "MALE",<br>    "contact":{<br>        "phoneNumber": "+1 8577305657",<br>        "email":"boora.k@northeastern.edu"<br>    },<br>    "address":{<br>        "street": "9 Dalwood Street",<br>        "apt": "Unit A",<br>        "city":"Boston",<br>        "state": "MA",<br>        "country": "United States of America",<br>        "pinCode": "500018"<br>    }<br><br>}</pre> |          No           |
|     GET     |     /api/v1/user      | Fetches all the users in the Database.                       | No Body Required                                                                                                                                                                                                                                                                                                                                                                                                                                           |          No           |
|     GET     | /api/v1/user/{userId} | Fetches the user whose Id is mentioned in the PathVariable.  | No Body Required                                                                                                                                                                                                                                                                                                                                                                                                                                           |          No           |
|     PUT     | /api/v1/user/{userId} | Updates the user with new user provided in the body.         | <pre>{<br> "firstName":"Boora",<br>"lastName":"Kaushik",<br>"dob":"1998-12-10",<br>"gender": "MALE"<br>}</pre>                                                                                                                                                                                                                                                                                                                                             |          No           |
|   DELETE    | /api/v1/user/{userId} | Deletes the user whose id is provided in the path variable.  | No Body Required                                                                                                                                                                                                                                                                                                                                                                                                                                           |          No           |

### Restaurant Entity
This Resource requires users to be authenticated. JWT must be included in the header.
| HTTP Method |         Path          | Description                                                  | Example Body                                                                                                                                                                                                                                                                                                                                                                                                                                               | Requires Bearer Token |
| :---------: | :-------------------: | :----------------------------------------------------------- | :--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | :-------------------: |
POST |/api/v1/restaurant/|Adds restaurant to DB. Expects the restaurant to be present in the body. | <pre>{<br>    "name":"Kaushik Restaurent",<br>    "cusine":"Indian",<br>    "contact":{<br>        "phoneNumber": "+1 8575551234",<br>        "email":"boora.k@northeastern.edu"<br>    },<br>    "address":{<br>        "street": "9 Dalwood Street",<br>        "apt": "Unit A",<br>        "city":"Boston",<br>        "state": "MA",<br>        "country": "United States of America",<br>        "pinCode": "500018"<br>    }<br>}</pre> | Yes
GET |/api/v1/restaurant|Fetches all the restaurants in the Database.| No Body Required | Yes
GET |/api/v1/restaurant/{restaurantId}|Fetches the restaurant whose Id is mentioned in the PathVariable.| No Body Required | Yes
PUT |/api/v1/restaurant/{restaurantId}| Updates the restaurant with new restaurant provided in the body. | <pre>{<br>    "name":"Boora Restaurent",<br>    "cusine":"Kaushik"<br>}</pre> | YES
DELETE |/api/v1/restaurant/{restaurantId}|Deletes the restaurant whose id is provided in the path variable.| No Body Required | YES

### Address Entity
This Resource requires users to be authenticated. JWT must be included in the header.
| HTTP Method |         Path          | Description                                                  | Example Body                                                                                                                                                                                                                                                                                                                                                                                                                                               | Requires Bearer Token |
| :---------: | :-------------------: | :----------------------------------------------------------- | :--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | :-------------------: |
POST|/api/v1/address/restaurant/{restaurantId}|Updates the Address of a Restaurant by creating a new address.| <pre>  {<br>    "street": "9 Dalwood Street",<br>    "apt": "Unit B",<br>    "city": "Boston",<br>    "state": "MA",<br>    "country": "United States of America",<br>    "pinCode": "500018"<br>}</pre> | YES
POST|/api/v1/address/user/{userId}|Updates the Address of a User by creating a new address.| <pre>  {<br>    "street": "9 Dalwood Street",<br>    "apt": "Unit B",<br>    "city": "Boston",<br>    "state": "MA",<br>    "country": "United States of America",<br>    "pinCode": "500018"<br>}</pre> | YES
PUT|/api/v1/address|Updates the address with the provided address| <pre>  {<br>    "street": "9 Dalwood Street",<br>    "apt": "Unit B",<br>    "city": "Boston",<br>    "state": "MA",<br>    "country": "United States of America",<br>    "pinCode": "500018"<br>}</pre> | YES
GET|/api/v1/address|Fetches all the addresses in the Database.| No Body Required | YES
GET|/api/v1/address/{addressId}|Fetches the address whose Id is mentioned in the PathVariable.| No Body Required | YES

### Contact Entity
This Resource requires users to be authenticated. JWT must be included in the header.
| HTTP Method |         Path          | Description                                                  | Example Body                                                                                                                                                                                                                                                                                                                                                                                                                                               | Requires Bearer Token |
| :---------: | :-------------------: | :----------------------------------------------------------- | :--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | :-------------------: |
POST|/api/v1/contact/restaurant/{restaurantId}|Updates the Contact of a Restaurant by creating a new contact.| <pre>  {<br>    "phoneNumber":"+1 8572305117",<br>    "email":"kaushikboora2@gmail.com"<br>}</pre> | YES
POST|/api/v1/contact/user/{userId}| Updates the Contact of a User by creating a new contact.| <pre>  {<br>    "phoneNumber":"+1 8572305117",<br>    "email":"kaushikboora2@gmail.com"<br>}</pre> | YES
PUT|/api/v1/contact|Updates the contact with the provided contact| <pre>  {<br>    "phoneNumber":"+1 8572305117",<br>    "email":"kaushikboora2@gmail.com"<br>}</pre> | YES
GET|/api/v1/contact|Fetches all the contacts in the Database.| No Body Required |YES
GET|/api/v1/contact/{contactId}|Fetches the contact whose Id is mentioned in the PathVariable.| No Body Required | YES

### Comment Entity
This Resource requires users to be authenticated. JWT must be included in the header.
| HTTP Method |         Path          | Description                                                  | Example Body                                                                                                                                                                                                                                                                                                                                                                                                                                               | Requires Bearer Token |
| :---------: | :-------------------: | :----------------------------------------------------------- | :--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | :-------------------: |
POST|/api/v1/comment/restaurant/{restaurantId}/user/{userId}|Adds comment to DB and links it with User and Restaurant. Expects the comment to be present in the body and restaurant id and user id provided in the path variable.| <pre>  {<br>    "comment":"Hello !Ndia"<br>}</pre> | YES
GET|/api/v1/comment|Fetches all the comments in the Database.| No Body Required | YES
GET|/api/v1/comment/{commentId}|Fetches the comment whose Id is mentioned in the PathVariable.| No Body Required | YES
PUT|/api/v1/comment/{commentId}|Updates the comment with new comment provided in the body.| <pre>  {<br>    "comment":"Hello!"<br>}</pre> | YES
DELETE|/api/v1/comment/{commentId}|Deletes the comment whose id is provided in the path variable.| No Body Required | YES

### Rating Entity
This Resource requires users to be authenticated. JWT must be included in the header.
| HTTP Method |         Path          | Description                                                  | Example Body                                                                                                                                                                                                                                                                                                                                                                                                                                               | Requires Bearer Token |
| :---------: | :-------------------: | :----------------------------------------------------------- | :--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | :-------------------: |
POST|/api/v1/rating/restaurant/{restaurantId}/user/{userId}|Adds rating to DB and links it with User and Restaurant. Expects the rating to be present in the body and restaurant id and user id provided in the path variable.| <pre>  {<br>    "value": 4<br>}</pre> | YES
GET|/api/v1/rating|Fetches all the ratings in the Database.| {} | YES
GET|/api/v1/rating/{ratingId}|Fetches the rating whose Id is mentioned in the PathVariable.| {} | YES
PUT|/api/v1/rating/{ratingId}|Updates the rating with new rating provided in the body.|<pre>  {<br>    "value": 3<br>}</pre> | YES
DELETE|/api/v1/rating/{ratingId}|Deletes the rating whose id is provided in the path variable. | {} | YES

### Food Entity
This Resource requires users to be authenticated. JWT must be included in the header.
| HTTP Method |         Path          | Description                                                  | Example Body                                                                                                                                                                                                                                                                                                                                                                                                                                               | Requires Bearer Token |
| :---------: | :-------------------: | :----------------------------------------------------------- | :--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | :-------------------: |
POST|/api/v1/food/|Adds food to DB.| <pre>{<br>    "name":"Biryani",<br>    "ingredients":"Samolina, Urad dal, Baking soda, Salt",<br>    "cusine":"Indian"<br>}</pre> | YES
GET|/api/v1/food|Fetches all the foods in the Database.| No Body Required | YES
GET|/api/v1/food/{foodId}|Fetches the food whose Id is mentioned in the PathVariable.| No Body Required | YES
PUT|/api/v1/food/{foodId}|Updates the food with new food provided in the body. | <pre>{<br>    "name":"Biryani",<br>    "ingredients":"Urad dal, Baking soda, Salt",<br>    "cusine":"Indian"<br>}</pre> | YES
DELETE|/api/v1/food/{foodId}|Deletes the food whose id is provided in the path variable.| No Body Required | YES

### MenuItem Entity
This Resource requires users to be authenticated. JWT must be included in the header.
| HTTP Method |         Path          | Description                                                  | Example Body                                                                                                                                                                                                                                                                                                                                                                                                                                               | Requires Bearer Token |
| :---------: | :-------------------: | :----------------------------------------------------------- | :--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | :-------------------: |
POST|/api/v1/menu-item/restaurant/{restaurantId}/food/{foodId}|Adds menuItem to DB and links it with Food and Restaurant. Expects the menuItem to be present in the body and restaurant id and Food id provided in the path variable.| <pre>{<br>    "price": 30.0,<br>    "calories":15.67<br>}</pre> | YES
GET|/api/v1/menu-item|Fetches all the menuItems in the Database.| {} | YES
GET|/api/v1/menu-item/{menuItemId}|Fetches the menuItem whose Id is mentioned in the PathVariable.| {} | YES
PUT|/api/v1/menu-item/{menuItemId}|Updates the menuItem with new menuItem provided in the body.| <pre>{<br>    "price": 76.0,<br>    "calories":15.67<br>}</pre> | YES
DELETE|/api/v1/menu-item/{menuItemId}|Deletes the menuItem whose id is provided in the path variable.| {} | YES


### MenuItem Entity
Creates a JWT that is valid for 12 hours.
| HTTP Method |         Path          | Description                                                  | Example Body                                                                                                                                                                                                                                                                                                                                                                                                                                               | Requires Bearer Token |
| :---------: | :-------------------: | :----------------------------------------------------------- | :--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | :-------------------: |
POST|/login|Takes username and password of a user, authenticates user and if valid creates a JWT token that is valid for 12 hrs since it is created.| <pre>{<br>            "userName": uName,<br>            "password": "Test@123"<br>        }<br></pre> | Not Applicable
## Documentation
Implemented Documentation using Java Docs. Please find documentation of all the classes under docs directory.

## Contributer

[Kaushik Boora](https://www.linkedin.com/in/kaushik-boora/)


In a dynamic and challenging venture, I took charge as a visionary leader, guiding a diverse team through uncharted territory. Amidst turbulent waters, I navigated with unwavering determination, fostering unity and igniting the team's passion for success. Through turbulent storms and towering obstacles, I led with resilience, turning challenges into stepping stones. Armed with technical expertise in C, Java, and Python, I propelled innovation and executed a project of monumental proportions. In this high-stakes journey, my unwavering commitment and problem-solving prowess forged a triumphant path, leaving an indelible mark on our shared triumph.