# How to Start Weather Rest API  - CLI


2) Get the Java Code from :- https://github.com/rimmigill1987/weather-api

3) To  run the application please follow instruction from there http://appsdeveloperblog.com/run-spring-boot-app-from-a-command-line/ 

	i) To run the application 
  		- 	mvn spring-boot:run
  
  	#OR

	ii)  you can use the following maven command to package your spring boot application as jar.
          --mvn clean package
           you will get the jar :-   weather-api/target/weather-api-0.0.1-SNAPSHOT.jar
     iii)   Run the jar 
       java -jar target/weather-api-0.0.1-SNAPSHOT.jar
       
3) Open http://localhost:8080/api/v1 -- you will get welcome message 

4) For API Documentation please open this http://localhost:8080/api/v1/swagger-ui.html


# For Test Cases com.weather.test
     1) Junit Test case : WeatherApiControllerJunitTest
    
    Try to write the Integration test cases  
      WeatherApiAppTest.java
      WeatherServieTest.java
      
# Caching 
 I have implemented the default @EnableCaching 
  "	@Cacheable("cities")" you will find in WeatherApiService
  
#Motivation
# Spring Boot FrameWork With IDE Eclipse
	The Spring Boot is the best framework to develop the Rest Api with java, because they provide complete feature to develop Micro-service architecture

 
      
       
	
 
