package com.github.hugovallada.documentacao.infra.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ApiHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ApiError handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
		List<String> errors = new ArrayList<>();
		
		exception.getBindingResult()
			.getFieldErrors().stream()
			.forEach(fieldError -> {
				errors.add("Field: " +  fieldError.getField().toUpperCase() + " -> " + fieldError.getDefaultMessage());
			});
		
		exception.getBindingResult()
			.getGlobalErrors()
			.stream()
			.forEach(globalError -> {
				errors.add("Global: " + globalError.getObjectName().toUpperCase() + " -> " + globalError.getDefaultMessage());
			});
		
		return buildApiError(HttpStatus.BAD_REQUEST, "Erro de validação", errors);
	}
	
	@ExceptionHandler(RegraNegocioException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ApiError handleRegraNegocioException(RegraNegocioException exception) {
		return buildApiError(HttpStatus.BAD_REQUEST, "Erro na regra de negócio", Collections.singletonList(exception.getMessage()));
	}
	
	private ApiError buildApiError(HttpStatus status, String message, List<String> errors) {
		return ApiError.builder()
				.code(status.value())
				.status(status.getReasonPhrase())
				.message(message)
				.error(errors)
				.timestamp(LocalDateTime.now())
				.build();
	}
	
	

}
