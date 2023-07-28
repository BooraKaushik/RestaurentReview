# Restaurant Review

## Introduction

Restaurent Review Repo is a Backend Application Built using technologies like Spring Boot, Hibernate, MySQL, JWT. This Application provides APIs so that users can comment and rate restaurants. This application allows users to create a profile, Restaurants to create a restaurant, food and menu entities. The users can comment on these restaurants. This application provides Authentication using JWT. Thereby to create a restaurant, comment a user must be authenticated and must include bearer token in header.

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
GET |/api/v1/restaurant|Fetches all the restaurants in the Database.| No Body Reuired | Yes
GET |/api/v1/restaurant/{restaurantId}|Fetches the restaurant whose Id is mentioned in the PathVariable.| No Body Reuired | Yes
PUT |/api/v1/restaurant/{restaurantId}| Updates the restaurant with new restaurant provided in the body. | <pre>{<br>    "name":"Boora Restaurent",<br>    "cusine":"Kaushik"<br>}</pre> | YES
DELETE |/api/v1/restaurant/{restaurantId}|Deletes the restaurant whose id is provided in the path variable.| No Body Reuired | YES

### Address Entity
This Resource requires users to be authenticated. JWT must be included in the header.
| HTTP Method |         Path          | Description                                                  | Example Body                                                                                                                                                                                                                                                                                                                                                                                                                                               | Requires Bearer Token |
| :---------: | :-------------------: | :----------------------------------------------------------- | :--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | :-------------------: |
POST|/api/v1/address/restaurant/{restaurantId}|Updates the Address of a Restaurant by creating a new address.| <pre>  {<br>    "street": "9 Dalwood Street",<br>    "apt": "Unit B",<br>    "city": "Boston",<br>    "state": "MA",<br>    "country": "United States of America",<br>    "pinCode": "500018"<br>}</pre> | YES
POST|/api/v1/address/user/{userId}|Updates the Address of a User by creating a new address.| <pre>  {<br>    "street": "9 Dalwood Street",<br>    "apt": "Unit B",<br>    "city": "Boston",<br>    "state": "MA",<br>    "country": "United States of America",<br>    "pinCode": "500018"<br>}</pre> | YES
PUT|/api/v1/address|Updates the address with the provided address| <pre>  {<br>    "street": "9 Dalwood Street",<br>    "apt": "Unit B",<br>    "city": "Boston",<br>    "state": "MA",<br>    "country": "United States of America",<br>    "pinCode": "500018"<br>}</pre> | YES
GET|/api/v1/address|Fetches all the addresses in the Database.|  | YES
GET/api/v1/address/{addressId}|Fetches the address whose Id is mentioned in the PathVariable.|  | YES

### Contact Entity
This Resource requires users to be authenticated. JWT must be included in the header.
| HTTP Method |         Path          | Description                                                  | Example Body                                                                                                                                                                                                                                                                                                                                                                                                                                               | Requires Bearer Token |
| :---------: | :-------------------: | :----------------------------------------------------------- | :--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | :-------------------: |

## Contributer

[Kaushik Boora](https://www.linkedin.com/in/kaushik-boora/)
