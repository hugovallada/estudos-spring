# Data Transfer Objects no Spring

## DTOs são usados para refinar os dados que serão recebidos ou retornados após uma requisição. O uso de DTOs permite uma separação eficiente de responsabilidade entre as camadas de uma RestApi.
## Uma entidade possui diversos atributos que muitas vezes não se tem necessidade de recebe-los ou retorná-los para o usuário. Alguns exemplos:
### id -> pode ser automaticamente gerado pelo banco de dados, nesse caso, não faz sentido recebe-lo
### senha -> É necessário receber, porém não faz sentido retornar a senha ou seu hash para o usuário
### idade -> Geralmente o usuário informar a data de nascimento e então não faz sentido pedir que ele informe a idade, porém existem situações onde seria importante ter acesso a idade atual do usuário, então no retorno você pode calcular a idade baseada em sua data de nascimento e retorná-la

## Exemplo em código do que foi discutido acima:
```java
    @Entity
    @Data
    public class Aluno{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String login;
        private String passwod;
        private LocalDate birthDate;
    }
```

### Veja que no código acima queremos o id, login, senha e data de nascimento, porém na hora de receber o request do usuário ele não deve enviar o id, pois o banco irá gerar um id automaticamente.Para isso, criaremos um RequestDTO:
#### Um detalhe, agora que usamos DTOs, é no DTO de request que iremos fazer nossas validações
```java
    @Data
    public class AlunoRequestDTO{
        @NotBlank
        private String login;
        
        @NotBlank
        private String password;
        
        @Past
        private LocalDate birthDate;
    }
```
### Esses serão os campos que iremos esperar do nosso usuário, mas na hora de salvar no banco, precisaremos de um objeto da entidade Aluno, para isso, precisamos de uma maneira de converter um AlunoRequestDTO para Aluno.
### Existem algumas bibliotecas que podem fazer isso como MapStruct e ModelMapper, mas também é possível fazer isso manualmente, como será demostrado:
### Dentro da classe AlunoRequestDTO criaremos um método toModel que retornará uma instância de Aluno.
```java
    public Aluno toModel(){
        return new Aluno(login, password, birthDate);
    }
```

### Agora que vimos como podemos realizar o request, veremos o response e então os dois em ação em um controller e service:
### Na resposta, queremos devolver o id que será gerado, mas não queremos devolver a senha, além disso queremos retornar a idade atual
```java
    @Data
    public class AlunoResponseDTO{
        private Long id;
        private String login;
        private Integer age;
        private LocalDate birthDate;
    }
```
### Agora que temos a classe response com os atributos que devem ser retornados, precisamos criar uma forma de gerar uma instância de AlunoResponseDTO a partir da entidade Aluno, para isso criaremos um método estático:
```java
    public AlunoResponseDTO toDto(Aluno aluno){
        return new AlunoResponseDTO(
            aluno.getId(),
            aluno.getLogin(),
            Period.between(alungo.getBirthDate(), LocalDate.now()).getYears(),
            aluno.getBirthDate()
        );
    }
```

