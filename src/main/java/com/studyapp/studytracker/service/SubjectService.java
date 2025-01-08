package com.studyapp.studytracker.service;

import com.studyapp.studytracker.model.Subject;
import com.studyapp.studytracker.model.User;
import com.studyapp.studytracker.exception.ResourceNotFoundException;
import com.studyapp.studytracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SubjectService {

    private final UserRepository userRepository;

    public SubjectService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Adicionar uma disciplina a uma prova
    public User addSubject(String userId, String examId, Subject subject) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Gerar um ID único para a disciplina (Subject)
            subject.setSubjectId(UUID.randomUUID().toString());

            user.getExams().stream()
                .filter(exam -> exam.getExamId().equals(examId))
                .findFirst()
                .ifPresentOrElse(
                    exam -> exam.getSubjects().add(subject), // Adiciona a disciplina à prova encontrada
                    () -> {
                        throw new ResourceNotFoundException("Exam not found with id: " + examId);
                    });
            
            return userRepository.save(user); // Persiste o usuário com a nova disciplina adicionada
        }
        throw new ResourceNotFoundException("User not found with id: " + userId);
    }

    // Remover uma disciplina de uma prova
    public User removeSubject(String userId, String examId, String subjectId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.getExams().stream()
                .filter(exam -> exam.getExamId().equals(examId))
                .findFirst()
                .ifPresentOrElse(
                    exam -> exam.getSubjects().removeIf(subject -> subject.getSubjectId().equals(subjectId)), // Remove a disciplina com o ID correspondente
                    () -> {
                        throw new ResourceNotFoundException("Exam not found with id: " + examId);
                    });
            
            return userRepository.save(user); // Persiste o usuário com a disciplina removida
        }
        throw new ResourceNotFoundException("User not found with id: " + userId);
    }

    // Atualizar o tempo total de estudo para uma disciplina
    public User updateStudyTime(String userId, String examId, String subjectId, double additionalTime) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.getExams().stream()
                .filter(exam -> exam.getExamId().equals(examId))
                .findFirst()
                .ifPresent(exam -> {
                    exam.getSubjects().stream()
                        .filter(subject -> subject.getSubjectId().equals(subjectId))
                        .findFirst()
                        .ifPresent(subject -> {
                            // Adiciona o tempo extra ao tempo total de estudo
                            subject.setStudyTime(subject.getStudyTime() + additionalTime);
                        });
                });
            
            return userRepository.save(user); // Persiste as alterações
        }
        throw new ResourceNotFoundException("User or Exam or Subject not found");
    }

    // Atualizar o tempo de estudo diário para uma disciplina
    public User addDailyStudyTime(String userId, String examId, String subjectId, double additionalTime) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.getExams().stream()
                .filter(exam -> exam.getExamId().equals(examId))
                .findFirst()
                .ifPresent(exam -> {
                    exam.getSubjects().stream()
                        .filter(subject -> subject.getSubjectId().equals(subjectId))
                        .findFirst()
                        .ifPresent(subject -> {
                            // Adiciona o tempo extra ao tempo de estudo diário
                            subject.setDailyStudyTime(subject.getDailyStudyTime() + additionalTime);
                        });
                });
            
            return userRepository.save(user); // Persiste as alterações
        }
        throw new ResourceNotFoundException("User or Exam or Subject not found");
    }

    // Recalcular relativeImportance e globalImportance
    public void recalculateImportance(String userId, String examId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            user.getExams().stream()
                .filter(exam -> exam.getExamId().equals(examId))
                .findFirst()
                .ifPresent(exam -> {
                    // Calcula o peso total das disciplinas na prova
                    double totalWeightExam = exam.getSubjects().stream()
                        .mapToDouble(Subject::getWeight)
                        .sum();

                    // Recalcula relativeImportance para cada disciplina na prova
                    exam.getSubjects().forEach(subject ->
                        subject.setRelativeImportance(calculateRelativeImportance(subject.getWeight(), totalWeightExam))
                    );

                    // Calcula o peso total das disciplinas do usuário
                    double totalWeightUser = user.getExams().stream()
                        .flatMap(innerExam -> innerExam.getSubjects().stream())
                        .mapToDouble(Subject::getWeight)
                        .sum();

                    // Recalcula globalImportance para cada disciplina na prova
                    exam.getSubjects().forEach(subject ->
                        subject.setGlobalImportance(calculateGlobalImportance(subject.getWeight(), totalWeightUser))
                    );
                });

            userRepository.save(user); // Persiste as alterações após o recálculo
        } else {
            throw new ResourceNotFoundException("User or Exam not found");
        }
    }

    // Método para calcular relativeImportance
    private double calculateRelativeImportance(double weight, double totalWeightExam) {
        return totalWeightExam == 0 ? 0 : (weight / totalWeightExam) * 100;
    }

    // Método para calcular globalImportance
    private double calculateGlobalImportance(double weight, double totalWeightUser) {
        return totalWeightUser == 0 ? 0 : (weight / totalWeightUser) * 100;
    }
}
