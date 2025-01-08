package com.studyapp.studytracker.controller;

import com.studyapp.studytracker.dto.SubjectDto;
import com.studyapp.studytracker.model.Subject;
import com.studyapp.studytracker.model.User;
import com.studyapp.studytracker.service.SubjectService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    /**
     * Adicionar uma nova disciplina a uma prova de um usuário.
     * @param userId ID do usuário
     * @param examId ID da prova
     * @param subjectDto Dados da disciplina a ser adicionada
     * @return Usuário atualizado com a nova disciplina adicionada
     */
    @PostMapping("/{userId}/{examId}")
    public User addSubject(
            @PathVariable String userId,
            @PathVariable String examId,
            @RequestBody SubjectDto subjectDto) {
        Subject subject = convertToModel(subjectDto);
        return subjectService.addSubject(userId, examId, subject);
    }

    /**
     * Atualizar o tempo total de estudo para uma disciplina.
     * @param userId ID do usuário
     * @param examId ID da prova
     * @param subjectId ID da disciplina
     * @param additionalTime Tempo adicional de estudo (em horas)
     * @return Usuário atualizado com o tempo de estudo ajustado
     */
    @PatchMapping("/{userId}/{examId}/{subjectId}/study-time")
    public User updateStudyTime(
            @PathVariable String userId,
            @PathVariable String examId,
            @PathVariable String subjectId,
            @RequestParam double additionalTime) {
        return subjectService.updateStudyTime(userId, examId, subjectId, additionalTime);
    }

    /**
     * Atualizar o tempo de estudo diário para uma disciplina.
     * @param userId ID do usuário
     * @param examId ID da prova
     * @param subjectId ID da disciplina
     * @param additionalTime Tempo adicional de estudo diário (em horas)
     * @return Usuário atualizado com o tempo de estudo diário ajustado
     */
    @PatchMapping("/{userId}/{examId}/{subjectId}/daily-study-time")
    public User addDailyStudyTime(
            @PathVariable String userId,
            @PathVariable String examId,
            @PathVariable String subjectId,
            @RequestParam double additionalTime) {
        return subjectService.addDailyStudyTime(userId, examId, subjectId, additionalTime);
    }

    /**
     * Recalcular os campos de importância relativa e global para as disciplinas.
     * @param userId ID do usuário
     * @param examId ID da prova
     */
    @PatchMapping("/{userId}/{examId}/recalculate-importance")
    public void recalculateImportance(
            @PathVariable String userId,
            @PathVariable String examId) {
        subjectService.recalculateImportance(userId, examId);
    }

    /**
     * Método privado para converter um DTO para o modelo de disciplina.
     * @param subjectDto Dados da disciplina
     * @return Objeto Subject (modelo)
     */
    private Subject convertToModel(SubjectDto subjectDto) {
        Subject subject = new Subject();
        subject.setName(subjectDto.getName());
        subject.setWeight(subjectDto.getWeight());
        return subject;
    }
}
