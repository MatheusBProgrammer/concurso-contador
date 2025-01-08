package com.studyapp.studytracker.dto;

import java.util.List;

public class UserDto {

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ExamDto> getExams() {
        return this.exams;
    }

    public void setExams(List<ExamDto> exams) {
        this.exams = exams;
    }
    private String email;
    private String password;
    private List<ExamDto> exams;

    // Getters, Setters, and Constructors
}
