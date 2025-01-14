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
     * Atualizar o tempo total de estudo para uma disciplina (em SEGUNDOS).
     * @param userId ID do usuário
     * @param examId ID da prova
     * @param subjectId ID da disciplina
     * @param additionalTime Tempo adicional de estudo (em segundos)
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
     * Atualizar o tempo de estudo diário para uma disciplina (em SEGUNDOS).
     * @param userId ID do usuário
     * @param examId ID da prova
     * @param subjectId ID da disciplina
     * @param additionalTime Tempo adicional de estudo diário (em segundos)
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
     * Editar uma disciplina (nome, peso, meta, etc.).
     * @param userId ID do usuário
     * @param examId ID da prova
     * @param subjectId ID da disciplina
     * @param subjectDto Campos que devem ser atualizados
     * @return Usuário atualizado após a edição da disciplina
     */
    @PutMapping("/{userId}/{examId}/{subjectId}")
    public User updateSubject(
            @PathVariable String userId,
            @PathVariable String examId,
            @PathVariable String subjectId,
            @RequestBody SubjectDto subjectDto) {
        // Converte o DTO para o modelo "Subject"
        Subject updatedSubject = convertToModel(subjectDto);
        // Chamamos o método de serviço para atualizar
        return subjectService.updateSubject(userId, examId, subjectId, updatedSubject);
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
     * Note que aqui estamos apenas convertendo o que faz sentido na adição/edição.
     * Caso seja necessário atualizar studyTime e dailyStudyTime diretamente,
     * você pode ajustar este método conforme desejado.
     */
    private Subject convertToModel(SubjectDto subjectDto) {
        Subject subject = new Subject();
        subject.setName(subjectDto.getName());
        subject.setWeight(subjectDto.getWeight());
        subject.setStudyGoal(subjectDto.getStudyGoal());
        // Caso queira permitir a edição de studyTime/dailyStudyTime diretamente:
        // subject.setStudyTime(subjectDto.getStudyTime());
        // subject.setDailyStudyTime(subjectDto.getDailyStudyTime());
        return subject;
    }
    /**
 * Remover uma disciplina de uma prova.
 * @param userId ID do usuário
 * @param examId ID da prova
 * @param subjectId ID da disciplina
 * @return Usuário atualizado após a remoção da disciplina
 */
@DeleteMapping("/{userId}/{examId}/{subjectId}")
public User removeSubject(
        @PathVariable String userId,
        @PathVariable String examId,
        @PathVariable String subjectId) {
    return subjectService.removeSubject(userId, examId, subjectId);
}

}
