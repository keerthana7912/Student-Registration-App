# Student Registration System (Java Servlets + MongoDB)

A simple CRUD web application for registering and managing student records,
built with Java Servlets, JSP, and MongoDB.

## Features
- Register a new student (name, age, email, course, phone)
- View all registered students in a table
- Edit / update a student's details
- Delete a student record
- Server-side validation on the registration form

## Tech Stack
- Java 11
- Java Servlets 4.0 / JSP
- MongoDB (via the official `mongodb-driver-sync` Java driver, v4.11.1)
- Maven (build tool, packages the app as a `.war`)
- Apache Tomcat 9 or 10 (or any Servlet 4.0-compatible container)

## Project Structure
```
StudentRegistrationApp/
├── pom.xml
├── README.md
└── src/main/
    ├── java/com/studentapp/
    │   ├── model/Student.java          # POJO
    │   ├── dao/MongoConnection.java    # MongoDB client/connection holder
    │   ├── dao/StudentDAO.java         # CRUD operations
    │   └── servlet/
    │       ├── RegisterServlet.java    # POST /register
    │       ├── StudentListServlet.java # GET  /students
    │       ├── UpdateServlet.java      # GET+POST /update
    │       └── DeleteServlet.java      # GET  /delete
    └── webapp/
        ├── index.jsp     # registration form
        ├── students.jsp  # list of students
        ├── edit.jsp      # edit form
        ├── css/style.css
        └── WEB-INF/web.xml
```

## Prerequisites
1. **JDK 11+** installed.
2. **Maven 3.6+** installed.
3. **MongoDB** running locally on the default port (`27017`), or a remote/Atlas
   connection string.
4. **Apache Tomcat 9 or 10** (or another servlet container) to deploy the WAR.

## Configuration
Open `src/main/java/com/studentapp/dao/MongoConnection.java` and update the
connection string / database name if needed:

```java
private static final String CONNECTION_STRING = "mongodb://localhost:27017";
private static final String DB_NAME = "student_registration_db";
```

For MongoDB Atlas, use a connection string like:
```
mongodb+srv://<username>:<password>@<cluster-url>/?retryWrites=true&w=majority
```

No manual collection setup is required — MongoDB creates the `students`
collection automatically the first time a student is registered.

## Build
From the project root:
```bash
mvn clean package
```
This produces `target/StudentRegistrationApp.war`.

## Deploy & Run
1. Make sure MongoDB is running:
   ```bash
   mongod
   ```
2. Copy the WAR file into Tomcat's `webapps/` directory:
   ```bash
   cp target/StudentRegistrationApp.war /path/to/tomcat/webapps/
   ```
3. Start Tomcat:
   ```bash
   /path/to/tomcat/bin/startup.sh
   ```
4. Open your browser at:
   ```
   http://localhost:8080/StudentRegistrationApp/
   ```

## Usage
- **Register:** Fill in the form on the home page and submit.
- **View All:** Click "View All Students" to see the full list.
- **Edit:** Click "Edit" next to a record, change the fields, and save.
- **Delete:** Click "Delete" next to a record (confirmation prompt shown).

## Notes / Possible Extensions
- Add pagination and search/filter on the student list.
- Add authentication (login) for admin-only access.
- Add unique-email validation before insert.
- Switch `System.out` debugging to a proper logging framework (e.g. SLF4J)
  for production use.
- Replace inline JSP scriptlets with JSTL/EL for a cleaner view layer if
  extending this further.
