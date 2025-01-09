package com.studyapp.studytracker.dto;

public class SubjectDto {
    private String subjectId; // Adicionado o terceiro campo
    private String name;
    private double weight;

    // Construtor que aceita os três parâmetros
    public SubjectDto(String subjectId, String name, double weight) {
        this.subjectId = subjectId;
        this.name = name;
        this.weight = weight;
    }

    // Getters e Setters
    public String getSubjectId() {
        return this.subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
