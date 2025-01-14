package com.studyapp.studytracker.controller;

import com.studyapp.studytracker.dto.ExamDto;
import com.studyapp.studytracker.model.Exam;
import com.studyapp.studytracker.model.Subject;
import com.studyapp.studytracker.model.User;
import com.studyapp.studytracker.service.ExamService;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    private final ExamService examService;

    // Injeta o serviço de exam
    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    /**
     * Adiciona um exame para um usuário.
     *
     * @param userId  ID do usuário
     * @param examDto DTO contendo os dados do exame
     * @return Usuário atualizado com o novo exame
     */
    @PostMapping("/{userId}")
    public User addExam(@PathVariable String userId, @RequestBody ExamDto examDto) {
        Exam exam = convertToModel(examDto);
        return examService.addExam(userId, exam);
    }

    /**
     * Remove um exame de um usuário.
     *
     * @param userId ID do usuário
     * @param examId ID do exame
     * @return Usuário atualizado após a remoção
     */
    @DeleteMapping("/{userId}/{examId}")
    public User removeExam(@PathVariable String userId, @PathVariable String examId) {
        return examService.removeExam(userId, examId);
    }

    /**
     * Converte o DTO de Exam para o modelo de domínio.
     *
     * @param examDto Objeto DTO do exame
     * @return Objeto modelo de Exam
     */
    private Exam convertToModel(ExamDto examDto) {
        Exam exam = new Exam();
        exam.setName(examDto.getName());
        exam.setTotalWeight(examDto.getTotalWeight());

        // Converte a lista de SubjectDto para Subject
        if (examDto.getSubjects() != null) {
            exam.setSubjects(
                    examDto.getSubjects().stream()
                            .map(subjectDto -> {
                                Subject subject = new Subject();
                                subject.setSubjectId(subjectDto.getSubjectId());
                                subject.setName(subjectDto.getName());
                                subject.setWeight(subjectDto.getWeight());
                                return subject;
                            })
                            .collect(Collectors.toList()));
        }
        return exam;
    }

    /**
     * Atualiza um exame de um usuário (edição de nome, totalWeight, etc.).
     *
     * @param userId  ID do usuário
     * @param examId  ID do exame
     * @param examDto DTO com os campos atualizados
     * @return Usuário atualizado após a edição do exame
     */
    @PutMapping("/{userId}/{examId}")
    public User updateExam(@PathVariable String userId,
            @PathVariable String examId,
            @RequestBody ExamDto examDto) {
        Exam updatedExam = convertToModel(examDto);
        return examService.updateExam(userId, examId, updatedExam);
    }
}
