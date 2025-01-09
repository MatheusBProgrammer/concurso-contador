package com.studyapp.studytracker.dto;

import java.util.List;

public class ExamDto {
    private String examId;
    private String name;
    private List<SubjectDto> subjects;
    private double totalWeight;

    // Construtor
    public ExamDto(String examId, String name, List<SubjectDto> subjects, double totalWeight) {
        this.examId = examId;
        this.name = name;
        this.subjects = subjects;
        this.totalWeight = totalWeight;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubjectDto> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectDto> subjects) {
        this.subjects = subjects;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }
}
