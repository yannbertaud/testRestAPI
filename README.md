# testRestAPI
example of testing rest api using java

this example makes use of the spring rest api implemented in https://github.com/yannbertaud/springRestAPI. 
Clone the above repo and from the root folder of that repo run:

```mvn spring-boot:run```

from a seperate shell, run:

```mvn clean install```

should get something like the following output:
```
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running test.java.testRestAPI.TestUser

Sending 'GET' request to URL : http://localhost:8080/users/5
Response Code : 404
response is null

Sending 'GET' request to URL : http://localhost:8080/users/1
Response Code : 200
{"firstName":"yann","lastName":"bertaud","email":null,"password":null,"userId":1}
output from server:
{"firstName":"John","lastName":"smith","email":"johnsmith@gmail.com","password":null,"userId":0}
deleting user
404 Not Found
deleting user
202 Accepted
Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.264 sec

Results :

Tests run: 5, Failures: 0, Errors: 0, Skipped: 0

[INFO] 
[INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ testRestAPI ---
[INFO] Building jar: /Users/yannbertaud/Documents/eStore/testRestAPI/target/testRestAPI-0.0.1-SNAPSHOT.jar
[INFO] 
[INFO] --- maven-install-plugin:2.4:install (default-install) @ testRestAPI ---
[INFO] Installing /Users/yannbertaud/Documents/eStore/testRestAPI/target/testRestAPI-0.0.1-SNAPSHOT.jar to /Users/yannbertaud/.m2/repository/testRestAPI/testRestAPI/0.0.1-SNAPSHOT/testRestAPI-0.0.1-SNAPSHOT.jar
[INFO] Installing /Users/yannbertaud/Documents/eStore/testRestAPI/pom.xml to /Users/yannbertaud/.m2/repository/testRestAPI/testRestAPI/0.0.1-SNAPSHOT/testRestAPI-0.0.1-SNAPSHOT.pom
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 1.860 s
[INFO] Finished at: 2015-06-16T13:34:26-07:00
[INFO] Final Memory: 19M/307M
[INFO] ------------------------------------------------------------------------
```



