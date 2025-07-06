# News Aggregator API

A Spring Boot REST API for user registration, login, managing news preferences, and fetching logged in user preferred news from external APIs.  
**Authentication is session-based (not JWT)**. Upon successful login, the session ID is set in a cookie and used for subsequent requests.

---

## Features

- **Session-based authentication** using Spring Security (no JWT)
- User registration with email verification
- Manage and retrieve user news preferences
- Fetch news from external APIs based on preferences
- In-memory H2 database for development/testing
- Predefined endpoints for easy integration and testing
- **Note:** Input validation is not implemented

---

## Getting Started

### Prerequisites

- Java 17+
- Maven

### Setup Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/airtribe-projects/news-aggregator-api-with-spring-boot-Yogesh6734.git
   cd news-aggregator-api-with-spring-boot-Yogesh6734
   ```

2. **Build and Run**
   ```bash
   mvn spring-boot:run
   ```
   - The app will start on port **8080**
   - H2 console available at: [http://localhost:8080/api/h2-console](http://localhost:8080/api/h2-console)

---

## API Endpoints & Usage

### 1. **Register User**

- **Endpoint:** `POST /api/register`
- **Body:**
  ```json
  {
      "email": "yogeshbhati6734@gmail.com",
      "password": "pass",
      "name": "yogesh"
  }
  ```
- **Response:** Registered user details
- **Note:** A verification token is generated and printed in logs

---

### 2. **Verify Email Token**

- **Endpoint:** `POST /api/verifyToken?token=<your-token>`
- **Example:**  
  `http://localhost:8080/api/verifyToken?token=16e1464d-0aee-42d4-910c-0cd5748bd4ca`
- **Response:**  
  `Token verified successfully` (if valid)

---

### 3. **User Login (Sign In)**

- **Endpoint:** `POST /api/signin?name=<username>&password=<password>`
- **Example:**  
  `http://localhost:8080/api/signin?name=yogesh&password=pass`
- **Response:**  
  `Authentication successful`  
- **Important:**  
  The session ID is set in the cookie upon successful login. Use this cookie for subsequent authenticated requests.

---

### 4. **Create News Types**

- **Endpoint:** `POST /api/newsType`
- **Body:**
  ```json
  [
    { "newsType": "Technology" },
    { "newsType": "Sports" },
    { "newsType": "Business" }
  ]
  ```
- **Response:** Created news type objects

---

### 5. **Set User Preferences**

- **Endpoint:** `PUT /api/preferences`
- **Body:**
  ```json
  {
    "preferences": [1, 2, 3]
  }
  ```
- **Headers:**  
  Must include the session cookie from login
- **Response:**  
  `Preferences updated successfully.`

---

### 6. **Get User Preferences**

- **Endpoint:** `GET /api/preferences`
- **Headers:**  
  Must include the session cookie from login
- **Response:**
  ```json
  [
    {
      "id": 1,
      "preferenceName": "Technology"
    },
    ...
  ]
  ```

---

### 7. **Fetch News Based on Preferences**

- **Endpoint:** `GET /api/news`
- **Headers:**  
  Must include the session cookie from login
- **Response:**  
  News articles from external APIs based on user preferences

---

## Example CURL Commands

**Register User:**
```bash
curl -X POST http://localhost:8080/api/register \
  -H "Content-Type: application/json" \
  -d '{"email":"user@example.com","password":"pass","name":"user1"}'
```

**Verify Token:**
```bash
curl -X POST "http://localhost:8080/api/verifyToken?token=<your-token>"
```

**Login (capture session cookie):**
```bash
curl -i -X POST "http://localhost:8080/api/signin?name=user1&password=pass"
# Save the 'Set-Cookie' header value for use in subsequent requests
```

**Set Preferences:**
```bash
curl -X PUT http://localhost:8080/api/preferences \
  -H "Content-Type: application/json" \
  -H "Cookie: JSESSIONID=<session-id>" \
  -d '{"preferences":[1,2,3]}'
```

**Get News:**
```bash
curl -X GET http://localhost:8080/api/news \
  -H "Cookie: JSESSIONID=<session-id>"
```

---

## Notes

- **Session-based authentication:** Always include the cookie header (`JSESSIONID`) returned by `/signin` for all endpoints that require authentication
- **No input validation:** The API does not validate request bodies for correctness or format
- **Email verification:** Registration requires verification via a token printed in server logs
- **H2 Database:** Data resets on server restart

---

## Technologies Used

- Spring Boot
- Spring Security (Session-based)
- Spring Web
- Spring Data JPA
- H2 Database
- WebClient (for external API calls)
- Lombok
