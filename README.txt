Prequosites;
- Java 17
- Docker
- Postman (or similar)


PROJECT DETAILS:
"client-console-app" is a client side console application written in Java with Eclipse IDE. It sends required HTTP REST requests to server and serves data to client.

"spring-server-app" is a REST API written in Spring with IntelliJ IDE. It uses MongoDB ORM to persist data to non-relational database.

Spring project is dockerized and the client-console-app.jar is available for the execution.

DEMO;
- Below steps should be reproduced in order for the DEMO;

Start the mongodb container;
1- docker pull mongo:latest
2- docker run -d --name mongo-on-docker -p 27017:27017 mongo

Start the spring rest server application container;
1- cd to "\atm-app\spring-server-app\spring-server-app" folder
2- docker build -t spring_rest_api .
3- docker run -d --name springapplication-on-docker -p 8080:8080 spring_rest_api

Use Postman to add some accounts to database for the demo;
1- Open postman and create a PUT request
2- Set Authorization to "No Auth"
2- Set URL to http://localhost:8080/accounts/new/1111222233334444/1234	(card number should be 16 and PIN should be 4 numerical characters)
3- Send the request to the server

Available urls for REST API;
  URL template is http://localhost:8080/accounts/login/{cardNumber}/{pin} or http://localhost:8080/accounts/login/{cardNumber}/{amount}
- GET request for all account details - http://localhost:8080/accounts/login/1111222233334444/1234
- GET request for the balance - http://localhost:8080/accounts/balance/1111222233334444
- PUT request for deposit - http://localhost:8080/accounts/deposit/1111222233334444/1500
- PUT request for withdraw - http://localhost:8080/accounts/withdraw/1111222233334444/500
- PUT request for card pin change - http://localhost:8080/accounts/changePin/1111222233334444/4321
- PUT request for account creation - http://localhost:8080/accounts/new/1111222233334444/1234

To run the client console application;
1- cd to "atm-app\client-console-app" folder
2- java -jar client-console-app.jar
