<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Student Registration</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <h1>Student Registration System</h1>

    <nav>
        <a href="index.jsp">Register Student</a>
        <a href="students">View All Students</a>
    </nav>

    <% if (request.getAttribute("error") != null) { %>
        <div class="alert alert-error"><%= request.getAttribute("error") %></div>
    <% } %>

    <form action="register" method="post">
        <div class="form-group">
            <label for="name">Full Name *</label>
            <input type="text" id="name" name="name" placeholder="e.g. Priya Sharma" required>
        </div>

        <div class="form-group">
            <label for="age">Age *</label>
            <input type="number" id="age" name="age" min="1" max="120" placeholder="e.g. 20" required>
        </div>

        <div class="form-group">
            <label for="email">Email *</label>
            <input type="email" id="email" name="email" placeholder="e.g. priya@example.com" required>
        </div>

        <div class="form-group">
            <label for="course">Course</label>
            <input type="text" id="course" name="course" placeholder="e.g. B.Tech Computer Science">
        </div>

        <div class="form-group">
            <label for="phone">Phone</label>
            <input type="tel" id="phone" name="phone" placeholder="e.g. 9876543210">
        </div>

        <button type="submit" class="btn">Register Student</button>
    </form>
</div>
</body>
</html>
