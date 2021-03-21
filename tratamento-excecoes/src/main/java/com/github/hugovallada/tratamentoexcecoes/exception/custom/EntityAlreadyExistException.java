package com.github.hugovallada.tratamentoexcecoes.exception.custom;

@SuppressWarnings("serial")
public class EntityAlreadyExistException extends RuntimeException {

    public EntityAlreadyExistException(String message) {
        super(message);
    }

}
