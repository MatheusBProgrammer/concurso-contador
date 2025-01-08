package com.studyapp.studytracker.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "users") // Especifica a coleção no MongoDB
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private List<Exam> exams;

    // Construtor padrão
    public User() {}

    // Construtor com parâmetros
    public User(String name, String email, String password, List<Exam> exams) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.exams = exams;
    }

    // Getters e Setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }
}
