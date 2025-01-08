package com.studyapp.studytracker.exception;

/**
 * Exceção personalizada para recursos não encontrados.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Construtor padrão que aceita uma mensagem personalizada.
     *
     * @param message Mensagem descrevendo o erro.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Construtor que aceita uma mensagem e uma causa (exceção original).
     *
     * @param message Mensagem descrevendo o erro.
     * @param cause   Exceção original que causou o erro.
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
