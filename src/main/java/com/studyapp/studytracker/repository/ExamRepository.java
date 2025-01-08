package com.studyapp.studytracker.repository;

import com.studyapp.studytracker.model.Exam;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExamRepository extends MongoRepository<Exam, String> {
    // Adicione métodos personalizados aqui, se necessário
}
