# pizzeria API
### _This is an API for the basic operation of a pizzeria_:
![Static Badge](https://img.shields.io/badge/version-1.0-brightgreen)
![Static Badge](https://img.shields.io/badge/Java-17-brightgreen)

## Running the API

To run the API, you will need JDK version 17 installed on your machine.
[Download it here:](https://adoptium.net/es/temurin/releases/?version=17)

First, clone the repository:

```bash
git clone https://github.com/leiberbertel/java-pizzeria.git
cd leiber-pizzeria
```

Next, open your command terminal and located in the project root, execute the commands:

```bash
./gradlew build
```

```bash 
./gradlew bootRun
```

On OS windows

```bash
gradlew.bat build
```

```bash 
gradlew.bat bootRun
```

The Spring Boot application will launch and be running on port 8080

All endpoints and schemas are documented using Swagger UI. You can view the documentation at http://localhost:8080/swagger-ui/index.html#/, which is the default endpoint for the Swagger UI.

## Built with 🛠
* Java version 17 - Language used
* Lombok - Tertiary dependency
* Mysql - Database Engine
* Spring Framework - Framework used
* Spring Data JPA - Dependency on data manipulation
* Springdoc - Dependency on API documentation