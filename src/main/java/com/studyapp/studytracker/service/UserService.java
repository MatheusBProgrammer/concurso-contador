package com.studyapp.studytracker.service;

import com.studyapp.studytracker.exception.CustomException;
import com.studyapp.studytracker.model.User;
import com.studyapp.studytracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    // Injetando o repositório de usuários
    private final UserRepository userRepository;

    // Construtor para injeção de dependências
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Método para criar um novo usuário
     *
     * @param user Objeto User contendo os dados do novo usuário
     * @return O usuário salvo no banco de dados
     * @throws CustomException Se o email já estiver em uso
     */
    public User createUser(User user) {
        // Verifica se o email já existe no banco de dados
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new CustomException("Email já está em uso!", "USER_EMAIL_ALREADY_EXISTS");
        }
        // Salva o usuário no banco de dados
        return userRepository.save(user);
    }

    /**
     * Método para buscar um usuário por ID
     *
     * @param id ID do usuário
     * @return O usuário correspondente ao ID
     * @throws CustomException Se o usuário não for encontrado
     */
    public User getUserById(String id) {
        // Busca o usuário pelo ID no banco de dados
        return userRepository.findById(id)
                .orElseThrow(() -> new CustomException("Usuário não encontrado com o ID: " + id, "USER_NOT_FOUND"));
    }

    /**
     * Método para atualizar um usuário existente
     *
     * @param id          ID do usuário que será atualizado
     * @param updatedUser Objeto User com os novos dados
     * @return O usuário atualizado
     * @throws CustomException Se o usuário não for encontrado
     */
    public User updateUser(String id, User updatedUser) {
        return userRepository.findById(id).map(existingUser -> {
            // Atualiza os campos do usuário existente com os novos valores
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setExams(updatedUser.getExams());
            // Salva as alterações no banco de dados
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new CustomException("Usuário não encontrado com o ID: " + id, "USER_NOT_FOUND"));
    }

    /**
     * Método para excluir um usuário por ID
     *
     * @param id ID do usuário que será excluído
     * @throws CustomException Se o usuário não for encontrado
     */
    public void deleteUser(String id) {
        // Verifica se o usuário existe no banco de dados
        if (!userRepository.existsById(id)) {
            throw new CustomException("Usuário não encontrado com o ID: " + id, "USER_NOT_FOUND");
        }
        // Exclui o usuário do banco de dados
        userRepository.deleteById(id);
    }
}
