package com.studyapp.studytracker.controller;

import com.studyapp.studytracker.dto.ExamDto;
import com.studyapp.studytracker.dto.LoginRequest;
import com.studyapp.studytracker.dto.SubjectDto;
import com.studyapp.studytracker.dto.UserResponseDto;
import com.studyapp.studytracker.model.User;
import com.studyapp.studytracker.service.UserService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UserController {

    // Injetando o serviço de usuários
    private final UserService userService;

    // Construtor para injeção de dependências
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint para criar um novo usuário.
     */
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(201).body(createdUser);
    }

    /**
     * Endpoint para login de usuário.
     */
@PostMapping("/login")
public ResponseEntity<UserResponseDto> login(@RequestBody LoginRequest loginRequest) {
    // Autenticar o usuário
    User user = userService.login(loginRequest.getEmail(), loginRequest.getPassword());

    // Converter os exames de Exam para ExamDto
    List<ExamDto> examDtos = user.getExams().stream()
        .map(exam -> new ExamDto(
            exam.getExamId(),
            exam.getName(),
            exam.getSubjects().stream()
                .map(subject -> new SubjectDto(
                    subject.getSubjectId(),
                    subject.getName(),
                    subject.getWeight()
                )).toList(),
            exam.getTotalWeight()
        )).toList();

    // Construir o UserResponseDto
    UserResponseDto userResponse = new UserResponseDto(
        user.getId(),
        user.getName(),
        user.getEmail(),
        examDtos
    );

    // Retornar o DTO sem expor a senha
    return ResponseEntity.ok(userResponse);
}

    /**
     * Endpoint para buscar um usuário por ID
     *
     * @param id ID do usuário
     * @return ResponseEntity com o usuário encontrado
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        // Chama o serviço para buscar o usuário pelo ID
        User user = userService.getUserById(id);
        // Retorna o usuário encontrado
        return ResponseEntity.ok(user);
    }

    /**
     * Endpoint para atualizar um usuário por ID
     *
     * @param id          ID do usuário que será atualizado
     * @param updatedUser Objeto User com os novos dados
     * @return ResponseEntity com o usuário atualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User updatedUser) {
        // Chama o serviço para atualizar o usuário
        User user = userService.updateUser(id, updatedUser);
        // Retorna o usuário atualizado
        return ResponseEntity.ok(user);
    }

    /**
     * Endpoint para excluir um usuário por ID
     *
     * @param id ID do usuário que será excluído
     * @return ResponseEntity sem conteúdo (HTTP 204)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        // Chama o serviço para excluir o usuário
        userService.deleteUser(id);
        // Retorna um código HTTP 204 (No Content)
        return ResponseEntity.noContent().build();
    }
}
