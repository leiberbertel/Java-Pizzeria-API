# pizzeria API
### _This is an API for the basic operation of a pizzeria_:
![Static Badge](https://img.shields.io/badge/version-1.0-brightgreen)
![Static Badge](https://img.shields.io/badge/Java-17-brightgreen)

## Running the API with Docker
To run the API using Docker, follow these steps:

1. **Clone the Repository** (if you haven't already):

   ```bash
   git clone https://github.com/leiberbertel/java-pizzeria.git
   cd java-pizzeria

2. **Build the Docker Image**: <br>
Make sure Docker is installed on your machine. Build the Docker image with:
   ```bash
   docker build -t pizzeria-api
   ```
3. **Run the Docker Container**: <br>
After building the image, run the container with:
    ```bash
    docker run -p 8080:8080 pizzeria-api
    ```
4. **Access Swagger UI**: <br>
Once the container is running, you can view the Swagger UI documentation at http://localhost:8080/swagger-ui/index.html#/.

## Running the API Without Docker
To run the API, you will need JDK version 17 installed on your machine.
[Download it here:](https://adoptium.net/es/temurin/releases/?version=17)

1. **Clone the repository** (if you haven't already):
    ```bash
    git clone https://github.com/leiberbertel/java-pizzeria.git
    cd leiber-pizzeria
    ```

2. **Build and Run the Application:** <br>
    ```bash
    ./gradlew build
    ./gradlew bootRun
    ```

    **On OS windows**:
    ```bash
    gradlew.bat build
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
