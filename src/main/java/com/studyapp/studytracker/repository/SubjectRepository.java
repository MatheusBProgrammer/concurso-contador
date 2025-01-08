package com.studyapp.studytracker.repository;

import com.studyapp.studytracker.model.Subject;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubjectRepository extends MongoRepository<Subject, String> {
    // Adicione métodos personalizados aqui, se necessário
}
