package com.studentapp.servlet;

import com.studentapp.dao.StudentDAO;
import com.studentapp.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private final StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String ageStr = request.getParameter("age");
        String email = request.getParameter("email");
        String course = request.getParameter("course");
        String phone = request.getParameter("phone");

        // Basic server-side validation
        if (name == null || name.trim().isEmpty()
                || email == null || email.trim().isEmpty()
                || ageStr == null || ageStr.trim().isEmpty()) {
            request.setAttribute("error", "Name, age, and email are required fields.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageStr.trim());
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Age must be a valid number.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        Student student = new Student(name.trim(), age, email.trim(), course, phone);
        studentDAO.addStudent(student);

        response.sendRedirect("students?success=registered");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
