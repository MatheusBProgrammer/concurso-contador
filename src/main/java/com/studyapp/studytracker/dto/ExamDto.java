package com.studyapp.studytracker.dto;

import com.studyapp.studytracker.model.Subject;

import java.util.List;

public class ExamDto {
    private String name;
    private List<Subject> subjects;
    private double totalWeight;

    // Getters e Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }
}
