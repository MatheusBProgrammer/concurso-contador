package com.studyapp.studytracker.dto;

public class SubjectDto {
    private String subjectId; // Identificador único para a disciplina
    private String name; // Nome da disciplina
    private double weight; // Peso da disciplina
    private double relativeImportance; // Importância relativa da disciplina
    private double globalImportance; // Importância global da disciplina
    private double studyTime; // Tempo total de estudo
    private double dailyStudyTime; // Tempo diário de estudo
    private double studyGoal;

    // Construtor com todos os campos
    public SubjectDto(String subjectId, String name, double weight, double relativeImportance,
            double globalImportance, double studyTime, double dailyStudyTime, double studyGoal) {
        this.subjectId = subjectId;
        this.name = name;
        this.weight = weight;
        this.relativeImportance = relativeImportance;
        this.globalImportance = globalImportance;
        this.studyTime = studyTime;
        this.dailyStudyTime = dailyStudyTime;
        this.studyGoal = studyGoal;
    }

    // Getters e Setters
    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getRelativeImportance() {
        return relativeImportance;
    }

    public void setRelativeImportance(double relativeImportance) {
        this.relativeImportance = relativeImportance;
    }

    public double getGlobalImportance() {
        return globalImportance;
    }

    public void setGlobalImportance(double globalImportance) {
        this.globalImportance = globalImportance;
    }

    public double getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(double studyTime) {
        this.studyTime = studyTime;
    }

    public double getDailyStudyTime() {
        return dailyStudyTime;
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
