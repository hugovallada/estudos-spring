package com.github.hugovallada.documentacao.infra.exception;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApiError {
	private Integer code;
	
	private String status;
	
	private String message;
	
	private List<String> error;
	
	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/YYYY HH:mm:ss")
	private LocalDateTime timestamp;
	
}
