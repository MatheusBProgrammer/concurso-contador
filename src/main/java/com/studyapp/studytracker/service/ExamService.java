package com.studyapp.studytracker.service;

import com.studyapp.studytracker.exception.ResourceNotFoundException;
import com.studyapp.studytracker.model.Exam;
import com.studyapp.studytracker.model.User;
import com.studyapp.studytracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ExamService {

    private final UserRepository userRepository;

    // Injeta o repositório de usuários
    public ExamService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Adiciona uma nova prova para o usuário especificado.
     * 
     * @param userId ID do usuário
     * @param exam   Objeto Exam a ser adicionado
     * @return Usuário atualizado com a nova prova
     */
    // Adicionar uma nova prova a um usuário
    public User addExam(String userId, Exam exam) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Gerando um ID único para o exame
            exam.setExamId(UUID.randomUUID().toString());

            user.getExams().add(exam);
            return userRepository.save(user);
        }
        throw new ResourceNotFoundException("Usuário não encontrado com ID: " + userId);
    }

    /**
     * Remove uma prova específica de um usuário.
     * 
     * @param userId ID do usuário
     * @param examId ID do exame a ser removido
     * @return Usuário atualizado após a remoção do exame
     */
    public User removeExam(String userId, String examId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.getExams().removeIf(exam -> exam.getExamId().equals(examId));
            return userRepository.save(user);
        }
        throw new ResourceNotFoundException("Usuário não encontrado com ID: " + userId);
    }

    /**
     * Atualiza uma prova específica de um usuário.
     *
     * @param userId      ID do usuário
     * @param examId      ID do exame a ser atualizado
     * @param updatedExam Objeto Exam com os campos atualizados
     * @return Usuário atualizado após a edição do exame
     */

    public User updateExam(String userId, String examId, Exam updatedExam) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            user.getExams().stream()
                    .filter(exam -> exam.getExamId().equals(examId))
                    .findFirst()
                    .ifPresentOrElse(exam -> {
                        // Atualiza os campos do exame que foram enviados
                        exam.setName(updatedExam.getName());
                        exam.setTotalWeight(updatedExam.getTotalWeight());
                        // Se desejar atualizar a lista de subjects inteira, pode fazer:
                        // exam.setSubjects(updatedExam.getSubjects());
                    }, () -> {
                        throw new ResourceNotFoundException("Exame não encontrado com ID: " + examId);
                    });

            return userRepository.save(user);
        }
        throw new ResourceNotFoundException("Usuário não encontrado com ID: " + userId);
    }
}
