# Introuction
Project created to test out structure in coding, togetherewith React-structure, a front end app.

From this: 
Web tutor; https://www.baeldung.com/spring-boot-start
Git Repo: https://github.com/eugenp/tutorials/tree/master/spring-boot-modules/spring-boot-bootstrap

Also found a cool API; https://developers.themoviedb.org/3/getting-started/languages

# Status and work

## Simple JSON result with rooms in a JSON struct as return
- Java app with Persistence, Web Controller, API, some secutity
- Now reate app for AWS, with basic db, ++ for Lamda
- Create API, add database, send to AWS Docker registry, use ads Lamda from 
- Use AWS DynamoDB asd datastore
- 

## Add validaton of JWT token
- Next is to implement authentication with Microsoft technology. The react-structure fronten have it implemented. now we like to see how to get validation and data on server side wuth MSAL libraries
Links are:
  
## Check if OnBehalf of can be chanied from service to service

https://docs.microsoft.com/en-us/azure/active-directory/develop/msal-authentication-flows
- Goals
  - Valid token
  - Validate groups - access right
  - Trace original user and calling application

En video om app client og behalf of
https://www.youtube.com/watch?v=59YwW8FrLm8

Actions:
- Add MSAL to project
- <dependency>
    <groupId>com.microsoft.azure</groupId>
    <artifactId>msal4j</artifactId>
    <version>1.11.2</version>
</dependency>