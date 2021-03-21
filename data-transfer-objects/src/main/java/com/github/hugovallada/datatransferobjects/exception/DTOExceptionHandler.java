package com.github.hugovallada.datatransferobjects.exception;

import com.github.hugovallada.datatransferobjects.exception.custom.RegraNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class DTOExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ApiError handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        List<String> errors = new ArrayList<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(fieldError -> {
                    errors.add("Field: " + fieldError.getField().toUpperCase() + " - " + fieldError.getDefaultMessage());
                });

        exception.getBindingResult()
                .getGlobalErrors()
                .forEach(globalError -> {
                    errors.add("Global: " + globalError.getObjectName().toUpperCase() + " - " + globalError.getDefaultMessage());
                });

        return buildApiError(HttpStatus.BAD_REQUEST, "Um ou mais erros de validação aconteceram", errors);
    }

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ApiError handleRegraNegocio(RegraNegocioException exception) {
        return buildApiError(HttpStatus.BAD_REQUEST, "Violação da regra de negócio", Collections.singletonList(exception.getMessage()));
    }

    private ApiError buildApiError(HttpStatus status, String message, List<String> errors) {
        return ApiError.builder()
                .code(status.value())
                .status(status.getReasonPhrase())
                .message(message)
                .errors(errors)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
