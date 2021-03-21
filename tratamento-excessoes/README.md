# Estudo de Tratamento de Exceções no Spring

## @RestControllerAdvice -> Usado para indicar que todos os métodos dessa classe já terão um response body e essa classe irá ouvir os controllers para pegar erros

## @ExceptionHandler(Exception.class) -> Indica qual classe de exceção será tratada pelo método anotado. Pode conter mais de uma classe como argumento

### Dicas para criar um bom tratamento de erros:

### 1 -> Criar uma classe que servirá como retorno para exceções:

    @Data @Builder -> gera um padrão builder para a classe
    public class ApiError{
        private int code; -> código do erro
        private String status; -> status do erro
        private String message; -> mensagem de erro
        private List<string> errors; -> Lista de erros
        private LocalDateTime timestamp; -> Data e Hora do erro
    }

### 2 -> Criar a classe que irá ouvir os controllers e gerar um método privado para preparar a classe de retorno:

    @RestControllerAdvice -> Os métodos já terão seu retorno dentro de um response body
    public class HandleException{

        private ApiError buildApiError(HttpStatus status, String message, List<String> errors){
            return ApiError.builder()
                .code(status.value())
                .status(status.getReasonPhrase())
                .message(message)
                .errors(errors)
                .timestamp(LocalDateTime.now())
                .build();
        }
    }

### 3 -> Criar os métodos para tratar os erros necessários:

    MÉTODO PARA TRATAMENTO DE ERROS PADRÕES
    
    
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ApiError handleNoSuchElementException(NoSuchElementException ex) {
        return buildApiError(HttpStatus.NOT_FOUND, ex.getMessage(), Collections.singletonList(ex.getMessage()));
    }
    
    


    MÉTODO PARA TRATAMENTO DE ERROS DE VALICAÇÃO

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ApiError handleMethodArgumentNotValidException(MethodArgumentNotValidaException ex) {
        List<String> errors = new ArrayList<>();

        ex.getBindingResults()
            .getFieldErrors()
            .forEach(fieldError -> {
                errors.add("Field: " + fieldError.getField() + " - " + fieldError.getDefaultMessage());
            });

        ex.getBindingResults()
            .getGlobalErrors()
            .forEach(globalError -> {
                errors.add("Global: " + globalError.getObjectName() + " - " + globalError.getDefaultMessage());
            });

        return buildApiError(HttpStatus.BAD_REQUEST,"Um ou mais campos falharam na validação", errors);
    }
