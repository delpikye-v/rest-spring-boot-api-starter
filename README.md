## Rest-spring-boot-template-api

#### Description
+ Spring Boot,
+ Spring Security,
+ Spring Websocket,
+ JWT,
+ JPA,
+ PostgresSQL
+ Logback
+ Maven
+ Java 8+

```shell
#### Install dependencies
mvn clean install
```

#### Run application
1.Run mode in CMD
```
mvn spring-boot:run
```

2.Debug mode in CMD
```
mvn spring-boot:run -Dspring-boot.run.profiles=foo,bar -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"
```

3.Run/Debug with Spring boot configuration in Intellij, Visual Studio, ...

#### Install PostGreSQL
```
spring.datasource.url=jdbc:postgresql://localhost:****/postgres
spring.datasource.username=*****
spring.datasource.password=*****

INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
```

##### Note
You can change another sql config or customize