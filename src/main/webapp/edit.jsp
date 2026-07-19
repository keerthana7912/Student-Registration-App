<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.studentapp.model.Student" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Student</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <h1>Edit Student Details</h1>

    <nav>
        <a href="index.jsp">Register Student</a>
        <a href="students">View All Students</a>
    </nav>

    <%
        Student student = (Student) request.getAttribute("student");
    %>

    <form action="update" method="post">
        <input type="hidden" name="id" value="<%= student.getId() %>">

        <div class="form-group">
            <label for="name">Full Name *</label>
            <input type="text" id="name" name="name" value="<%= student.getName() %>" required>
        </div>

        <div class="form-group">
            <label for="age">Age *</label>
            <input type="number" id="age" name="age" min="1" max="120" value="<%= student.getAge() %>" required>
        </div>

        <div class="form-group">
            <label for="email">Email *</label>
            <input type="email" id="email" name="email" value="<%= student.getEmail() %>" required>
        </div>

        <div class="form-group">
            <label for="course">Course</label>
            <input type="text" id="course" name="course" value="<%= student.getCourse() == null ? "" : student.getCourse() %>">
        </div>

        <div class="form-group">
            <label for="phone">Phone</label>
            <input type="tel" id="phone" name="phone" value="<%= student.getPhone() == null ? "" : student.getPhone() %>">
        </div>

        <button type="submit" class="btn">Save Changes</button>
        <a href="students" class="btn" style="background:#85929e; text-decoration:none; display:inline-block;">Cancel</a>
    </form>
</div>
</body>
</html>
