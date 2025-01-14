package com.studyapp.studytracker.model;

import org.springframework.data.annotation.Id;

public class Subject {

    @Id
    private String subjectId; // Identificador único para a disciplina
    private String name; // Nome da disciplina
    private double weight; // Peso da disciplina
    private double relativeImportance;
    private double globalImportance;
    private double studyTime;
    private double dailyStudyTime;
    private double studyGoal;

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

    public double getRelativeImportance() {
        return this.relativeImportance;
    }

    public void setRelativeImportance(double relativeImportance) {
        this.relativeImportance = relativeImportance;
    }

    public double getGlobalImportance() {
        return this.globalImportance;
    }

    public void setGlobalImportance(double globalImportance) {
        this.globalImportance = globalImportance;
    }

    public double getStudyTime() {
        return this.studyTime;
    }

    public void setStudyTime(double studyTime) {
        this.studyTime = studyTime;
    }

    public double getDailyStudyTime() {
        return this.dailyStudyTime;
    }

    public void setDailyStudyTime(double dailyStudyTime) {
        this.dailyStudyTime = dailyStudyTime;
    }

    public double getStudyGoal() {
        return studyGoal;
    }

    public void setStudyGoal(double studyGoal) {
        this.studyGoal = studyGoal;
    }
}
