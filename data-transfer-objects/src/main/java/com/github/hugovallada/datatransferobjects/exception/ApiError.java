package com.github.hugovallada.datatransferobjects.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ApiError {

    private int code;

    private String status;

    private String message;

    private List<String> errors;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/YYYY HH:mm:ss")
    private LocalDateTime timestamp;
}
