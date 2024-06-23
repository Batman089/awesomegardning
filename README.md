## Compose sample application
### Java application with Spring framework and a Postgres database

Project structure:
```
.
├── backend
│   ├── Dockerfile
│   └── ...
├── db
│   └── password.txt
├── compose.yaml
└── README.md
.
.


```

[_compose.yaml_](compose.yaml)
```
services:
  backend:
    build: backend
    ports:
    - 8080:8080
    environment:
      - POSTGRES_DB=gardnerdb
    networks:
      - spring-postgres
  db:
    image: postgres
    ...
```
The compose file defines an application with two services `backend` and `db`.
When deploying the application, docker compose maps port 8080 of the backend service container to port 8080 of the host as specified in the file.
Make sure port 8080 on the host is not already being in use.

## Deploy with docker compose

```
$ docker compose up -d
Creating network "spring-postgres_default" with the default driver
Building backend
Step 1/11 : FROM maven:3.5-jdk-17 AS build
3.5-jdk-17: Pulling from library/maven
...
Successfully tagged spring-postgres_backend:latest
WARNING: Image for service backend was built because it did not already exist. To rebuild this image you must use `docker-compose build` or `docker-compose up --build`.
Creating spring-postgres_backend_1 ... done
Creating spring-postgres_db_1      ... done
```

## Expected result

Listing containers must show two containers running and the port mapping as below:
```
$ docker ps
CONTAINER ID        IMAGE                     COMMAND                  CREATED             STATUS              PORTS                  NAMES
56236f640eaa        postgres                  "docker-entrypoint.s…"   29 seconds ago      Up 28 seconds       5432/tcp               spring-postgres_db_1
6e69472dc2c0        spring-postgres_backend   "java -Djava.securit…"   29 seconds ago      Up 28 seconds       0.0.0.0:8080->8080/tcp   spring-postgres_backend_1
```

After the application starts, navigate to `http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#` in your web browse for the Swagger UI.

Stop and remove the containers
