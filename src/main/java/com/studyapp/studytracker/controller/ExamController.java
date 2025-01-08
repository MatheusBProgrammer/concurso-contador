package com.studyapp.studytracker.controller;

import com.studyapp.studytracker.dto.ExamDto;
import com.studyapp.studytracker.model.Exam;
import com.studyapp.studytracker.model.User;
import com.studyapp.studytracker.service.ExamService;
import org.springframework.web.bind.annotation.*;

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
     * @param userId ID do usuário
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
        exam.setSubjects(examDto.getSubjects());
        return exam;
    }
}
