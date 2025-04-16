# FSD Backend (Spring Boot)

This backend project is a part of the FSD (Full Stack Development) system and manages core entities such as:

- **Teacher**
- **Supervisor**
- **Enrollment**
- **Hall Ticket**

It provides RESTful APIs to Create, Retrieve, and Delete data for these entities using Spring Boot.

---

## 📁 Folder Structure

```
FSD-Backend/
├── src/main/java/com/FSDProject/FSD/
│   ├── controller/
│   ├── entity/
│   ├── repository/
│   ├── service/
│   └── FsdApplication.java
├── src/main/resources/
│   └── application.properties
└── pom.xml
```

---

## ⚙️ Technologies Used

- Java 17+
- Spring Boot
- Spring Data JPA
- MySQL (or any other database)
- Maven

---

## 🛠️ How to Run Backend Code

### 1. Clone the repository
```bash
git clone https://github.com/siddhi22rachit/fsdBackend.git
cd fsdBackend
```

### 2. Open in an IDE (e.g., IntelliJ or Eclipse)
Make sure you have Java and Maven installed.

### 3. Configure Database
Update your `src/main/resources/application.properties` file:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/fsd_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

### 4. Build and Run
You can run the application via:
```bash
./mvnw spring-boot:run
```
OR by running `FsdApplication.java` from your IDE.

The backend server will start on: `http://localhost:8080`

---

## 📡 API Endpoints

### 🧑‍🏫 Teacher
```
POST    /teacher/create
GET     /teacher/all
DELETE  /teacher/delete/{id}
```

### 👨‍💼 Supervisor
```
POST    /supervisor/create
GET     /supervisor/all
DELETE  /supervisor/delete/{id}
```

### 📋 Enrollment
```
POST    /enrollment/create
GET     /enrollment/all
DELETE  /enrollment/delete/{id}
```

### 🎟️ Hall Ticket
```
POST    /hallticket/create
GET     /hallticket/all
DELETE  /hallticket/delete/{id}
```

---

## ✅ Postman Testing (Optional)
You can use Postman or any other API client to test your endpoints after running the server.

Example:
```json
POST /teacher/create
{
    "name": "Ravi",
    "email": "ravi@gmail.com",
    "department": "CSE",
    "subjectsTaught": ["DBMS", "Java"]
}
```

---

## 📝 Notes
- Ensure that MySQL or any chosen DB is up and running.
- Make sure CORS is enabled (`@CrossOrigin`) if connecting with frontend.
- Backend runs on port `8080` by default.

---

