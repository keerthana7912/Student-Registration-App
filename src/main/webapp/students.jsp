<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.studentapp.model.Student" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>All Students</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <h1>Registered Students</h1>

    <nav>
        <a href="index.jsp">Register Student</a>
        <a href="students">View All Students</a>
    </nav>

    <%
        String success = request.getParameter("success");
        if ("registered".equals(success)) {
    %>
        <div class="alert alert-success">Student registered successfully!</div>
    <% } else if ("updated".equals(success)) { %>
        <div class="alert alert-success">Student details updated successfully!</div>
    <% } else if ("deleted".equals(success)) { %>
        <div class="alert alert-success">Student record deleted successfully!</div>
    <% } %>

    <%
        List<Student> students = (List<Student>) request.getAttribute("students");
        if (students == null || students.isEmpty()) {
    %>
        <div class="empty-state">No students registered yet. <a href="index.jsp">Register the first one</a>.</div>
    <%
        } else {
    %>
    <table>
        <thead>
            <tr>
                <th>Name</th>
                <th>Age</th>
                <th>Email</th>
                <th>Course</th>
                <th>Phone</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
        <%
            for (Student s : students) {
        %>
            <tr>
                <td><%= s.getName() %></td>
                <td><%= s.getAge() %></td>
                <td><%= s.getEmail() %></td>
                <td><%= s.getCourse() == null ? "" : s.getCourse() %></td>
                <td><%= s.getPhone() == null ? "" : s.getPhone() %></td>
                <td>
                    <a class="action-link edit-link" href="update?id=<%= s.getId() %>">Edit</a>
                    <a class="action-link delete-link" href="delete?id=<%= s.getId() %>"
                       onclick="return confirm('Delete this student record?');">Delete</a>
                </td>
            </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <% } %>
</div>
</body>
</html>
