# Plant Health System

The **Plant Health System** is a Spring Boot web application that allows users to register, log in, and identify plants through a third-party plant identification API. Users can view their own profile details once logged in. This application is built using Spring Boot, Spring Security, Thymeleaf, and MySQL.

## Features

- **User Registration**: Allows users to register with a username and password.
- **User Authentication**: Enables secure login and logout for users.
- **Profile Page**: Displays logged-in user details.
- **Plant Identification**: Allows users to identify plants by sending data to a third-party API.
- **Home Page**: A welcoming page for users and visitors.
- **Secure Logout**: Provides secure logout functionality, clearing session data.

## Technologies Used

- **Java** (Spring Boot, Spring Security, Spring MVC)
- **Thymeleaf** for server-side rendering of HTML views
- **MySQL** as the database
- **RestTemplate** for external API calls

## Setup and Installation

### Prerequisites

- **Java 17** or higher
- **Maven** (for dependency management)
- **MySQL** (Ensure it’s running and you have the necessary credentials)

### Steps

1. **Clone the Repository**:

    ```bash
    git clone https://github.com/your-username/plant-health-system.git
    cd plant-health-system
    ```

2. **Database Configuration**:
   - Create a MySQL database named `plant_health` or update the `application.properties` file with your database configuration.

3. **Configure Application Properties**:
   - Update `src/main/resources/application.properties` with your database and API configurations:

     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/plant_health
     spring.datasource.username=your_db_username
     spring.datasource.password=your_db_password

     # Plant identification API key
     plant.id.api.key=your_api_key
     ```

4. **Run the Application**:
    ```bash
    ./mvnw spring-boot:run
    ```

5. **Access the Application**:
   - Open a browser and go to `http://localhost:8080`.

## API Endpoints

| Method | Endpoint               | Description                               |
|--------|-------------------------|-------------------------------------------|
| GET    | `/auth/register`        | User registration page                    |
| POST   | `/auth/register`        | Registers a new user                      |
| GET    | `/auth/login`           | User login page                           |
| POST   | `/auth/login`           | Authenticates user credentials            |
| GET    | `/auth/user-details`    | Displays logged-in user details           |
| GET    | `/plant/identify`       | Plant identification page                 |
| POST   | `/plant/identify`       | Sends plant data to third-party API       |
| GET    | `/auth/logout`          | Logs the user out and redirects to home   |
| GET    | `/`                     | Home page                                 |

## Directory Structure

## Usage

1. **Register a New User**:
   - Go to `/auth/register` and create an account with a username and password.

2. **Log In**:
   - Visit `/auth/login` and enter your credentials. Upon successful login, you will be redirected to the homepage.

3. **View User Details**:
   - Once logged in, go to `/auth/user-details` to view your profile information.

4. **Identify a Plant**:
   - Visit `/plant/identify`, submit the necessary plant details, and receive identification results from the third-party API.

5. **Log Out**:
   - Click the logout link to securely log out. This will clear your session and redirect you to the homepage.

## Security

The application uses Spring Security for user authentication and authorization, ensuring that sensitive endpoints are accessible only to logged-in users. Passwords are encrypted using BCrypt.

## Additional Information

- **Third-Party API**: The application integrates with the Plant ID API. You will need to obtain an API key and set it in `application.properties`.
- **Error Handling**: Custom 404 page handling can be added for improved user experience.

## Future Improvements

- **Forgot Password** functionality
- **Email Verification** during registration
- **Enhanced Error Handling** for external API calls

## License

This project is licensed under the MIT License.

