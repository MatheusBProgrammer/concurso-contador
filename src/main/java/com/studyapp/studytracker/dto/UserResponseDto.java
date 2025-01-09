package com.studyapp.studytracker.dto;

import java.util.List;

public class UserResponseDto {
    private String id;  
    private String name;
    private String email;
    private List<ExamDto> exams;

    // Construtor
    public UserResponseDto(String id, String name, String email, List<ExamDto> exams) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.exams = exams;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public List<ExamDto> getExams() {
        return this.exams;
    }

    public void setExams(List<ExamDto> exams) {
        this.exams = exams;
    }
}
