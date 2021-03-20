package com.github.hugovallada.tratamentoexcessoes.exception.custom;

@SuppressWarnings("serial")
public class EntityAlreadyExistException extends RuntimeException{
	
	public EntityAlreadyExistException(String message) {
		super(message);
	}

}
