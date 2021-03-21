package com.github.hugovallada.datatransferobjects.dtos.aluno;

import com.github.hugovallada.datatransferobjects.entity.Aluno;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
public class AlunoRequestDTO {

    @NotBlank(message = "O nome não pode ser nulo ou vazio")
    @Length(min = 3, max = 30, message = "O nome deve ter entre 3 e 30 caracteres")
    private String name;

    @NotBlank(message = "O ra não pode ser vazio ou nulo")
    @Length(min = 10, max = 10, message = "O ra deve ter 10 caracteres")
    private String ra;

    @NotBlank(message = "A senha não pode ser nula ou vazia")
    @Length(min = 6, max = 28, message = "A senha deve ter entre 6 e 28 catacteres")
    private String password;

    @NotBlank(message = "O login não pode ser nulo ou vazio")
    @Length(min = 4, max = 25, message = "O login deve ter entre 4 e 25 caracteres")
    private String login;

    @NotNull(message = "A data de nascimento não pode ser nula")
    @Past(message = "A data de nascimento não pode ser no futuro")
    private LocalDate birthDate;


    public Aluno toModel() {
        return new Aluno(name, ra, password, login, birthDate);
    }
}
