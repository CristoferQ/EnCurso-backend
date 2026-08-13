package com.encurso.domain.entity;

import com.encurso.domain.valueObject.Email;
import com.encurso.domain.valueObject.Password;

import java.util.ArrayList;
import java.util.List;

public class User {
    private Long id;
    private String name;
    private Email email;
    private Password password;
    private String role;
    private List<Enrollment> enrollments = new ArrayList<>();

    public User() {
    }

    public User(Long id, String name, String email, String password, String role) {
        this.id = id;
        this.name = name;
        this.email = email != null ? new Email(email) : null;
        this.password = password != null ? new Password(password) : null;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email != null ? email.value() : null;
    }

    public void setEmail(String email) {
        this.email = email != null ? new Email(email) : null;
    }

    public String getPassword() {
        return password != null ? password.value() : null;
    }

    public void setPassword(String password) {
        this.password = password != null ? new Password(password) : null;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + (email != null ? email.value() : null) + '\'' +
                ", role='" + role + '\'' +
                ", enrollments=" + enrollments +
                '}';
    }
}
