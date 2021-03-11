# spring-test
test

## MySQL
In order to use, you'll need to edit an application.properties file in src/main/resources:

```
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc://{mysql url}:{mysql port}/{dbName}?serverTimezone=UTC
spring.datasource.username={dbUser}
spring.datasource.password={dbPassword}
```
## Run
You can run the application like so:
```
mvn spring-boot:run
```
