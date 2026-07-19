package com.studentapp.model;

/**
 * Simple POJO representing a Student record.
 */
public class Student {

    private String id;         // MongoDB ObjectId as String
    private String name;
    private int age;
    private String email;
    private String course;
    private String phone;

    public Student() {
    }

    public Student(String name, int age, String email, String course, String phone) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.course = course;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
