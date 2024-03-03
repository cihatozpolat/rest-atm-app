To start the mongodb container;
1- docker pull mongo:latest
2- docker run -d --name mongo-on-docker -p 27017:27017 mongo

To start the server spring container;
1- cd to "\atm-app\server-rest-app\server-rest-app" folder
2- docker build -t spring_rest_api .
3- docker run -d --name springapplication-on-docker -p 8080:8080 spring_rest_api