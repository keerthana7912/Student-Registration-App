package com.studentapp.servlet;

import com.studentapp.dao.StudentDAO;
import com.studentapp.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {

    private final StudentDAO studentDAO = new StudentDAO();

    /** GET: load the student's current data into the edit form. */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        Student student = (id != null) ? studentDAO.getStudentById(id) : null;

        if (student == null) {
            response.sendRedirect("students");
            return;
        }

        request.setAttribute("student", student);
        request.getRequestDispatcher("edit.jsp").forward(request, response);
    }

    /** POST: save the changes submitted from the edit form. */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String ageStr = request.getParameter("age");
        String email = request.getParameter("email");
        String course = request.getParameter("course");
        String phone = request.getParameter("phone");

        int age = 0;
        try {
            age = Integer.parseInt(ageStr.trim());
        } catch (NumberFormatException | NullPointerException ignored) {
        }

        Student student = new Student(name, age, email, course, phone);
        student.setId(id);
        studentDAO.updateStudent(student);

        response.sendRedirect("students?success=updated");
    }
}
