package com.github.hugovallada.tratamentoexcessoes.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.github.hugovallada.tratamentoexcessoes.exception.custom.EntityAlreadyExistException;
import com.github.hugovallada.tratamentoexcessoes.exception.custom.RegraNegocioException;

@RestControllerAdvice
public class HandleExceptions {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ApiError handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		List<String> errors = new ArrayList<>();
		
		exception.getBindingResult().getFieldErrors()
			.forEach(fieldError -> {
				errors.add("Field: " + fieldError.getField().toUpperCase() +" - " + fieldError.getDefaultMessage());
			});
		
		exception.getBindingResult().getGlobalErrors()
			.forEach(globalError -> {
				errors.add("Global: " + globalError.getObjectName().toUpperCase() + " : " + globalError.getDefaultMessage());
			});
		
		return buildApiError(HttpStatus.BAD_REQUEST, "Um ou mais campos falharam na validação", errors);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ApiError handleNoSuchElementException(NoSuchElementException exception) {
		return buildApiError(HttpStatus.NOT_FOUND, exception.getMessage(), Collections.singletonList(exception.getMessage()));
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public ApiError handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
		return buildApiError(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), Collections.singletonList(exception.getMessage()));
	}
	
	@ExceptionHandler(EntityAlreadyExistException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ApiError handleEntityAlreadyExistsException(EntityAlreadyExistException exception) {
		return buildApiError(HttpStatus.BAD_REQUEST, exception.getMessage(), Collections.singletonList(exception.getMessage()));
	}
	
	@ExceptionHandler(RegraNegocioException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ApiError handleRegraNegocioException(RegraNegocioException exception) {
		return buildApiError(HttpStatus.BAD_REQUEST, "Problema na regra de negócio", Collections.singletonList(exception.getMessage()));
	}
	
	private ApiError buildApiError(HttpStatus status, String message ,List<String> errors) {
		ApiError apiError = ApiError.builder()
				.code(status.value())
				.status(status.getReasonPhrase())
				.timestamp(LocalDateTime.now())
				.message(message)
				.errors(errors)
				.build();
		
		return apiError;
	}

}
