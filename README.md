### How start the project

- Configure a PostgreSQL database:
  - It can be started easily with docker. Run command `docker-compose up` at the project root.
  - Or configure your own PostgreSQL database and change the configurations at the `application.properties` file.
- Make sure port 8080 is unoccupied;
- Run command `./mvnw spring-boot:run` at the project root.

### Doing a requst

Import the collection bellow on POSTMAN or another tool to do http requests:

[Download Collection](https://www.postman.com/collections/eea24c40158609084b62)

### Endpoints List

| Method | Path                            | Description                                                   |
|--------|---------------------------------|---------------------------------------------------------------|
| POST   | /planet-with-probes             | Register a planet and multiple probes                         |
| POST   | /planet-with-probes/planet/{id} | Register a probe on a particular planet                       |
| PATCH  | /planet-with-probes/probe/{id}  | Move a probe via commands                                     |

### Endpoints documentation

The documentation of endpoints are also availabe through swagger-ui. After running the application, access the address below.
```
http://localhost:8080/swagger-ui/index.html
```

### Monitoring

At the project was added the dependency *actuator* to monitor the application "health".
```
http://localhost:8080/actuator
http://localhost:8080/actuator/health
```
