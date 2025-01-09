package com.studyapp.studytracker.service;

import com.studyapp.studytracker.exception.CustomException;
import com.studyapp.studytracker.model.User;
import com.studyapp.studytracker.repository.UserRepository;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder(); // Encoder para hashear senhas
    }

    /**
     * Criar um novo usuário (com hashing de senha).
     */
    public User createUser(User user) {
        // Verifica se o email já existe no banco
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new CustomException("Email já está em uso!", "USER_EMAIL_ALREADY_EXISTS");
        }

        // Hashear a senha antes de salvar
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * Autenticar usuário pelo email e senha.
     *
     * @param email    Email do usuário.
     * @param password Senha do usuário.
     * @return Token ou mensagem de erro.
     */
    public User login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Verificar se a senha está correta
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user; // Substituir por token (exemplo: JWT)
            } else {
                throw new CustomException("Credenciais inválidas!", "INVALID_CREDENTIALS");
            }
        }

        throw new CustomException("Usuário não encontrado com o email: " + email, "USER_NOT_FOUND");
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
