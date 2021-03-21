package com.github.hugovallada.datatransferobjects.dtos.aluno;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.hugovallada.datatransferobjects.entity.Aluno;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;

@Data
public class AlunoResponseDTO {

    private Long id;

    private String name;

    private String ra;

    private String login;

    private Integer age;

    @JsonIgnore // ignora no retorno  - poderia usar o @JsonFormat para formatar a data
    private LocalDate birthDate;


    public static AlunoResponseDTO toDto(Aluno aluno) {
        return new AlunoResponseDTO(
                aluno.getId(),
                aluno.getName(),
                aluno.getRa(),
                aluno.getLogin(),
                Period.between(aluno.getBirthDate(), LocalDate.now()).getYears(),
                aluno.getBirthDate()
        );
    }

    public AlunoResponseDTO(Long id, String name, String ra, String login, Integer age, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.ra = ra;
        this.login = login;
        this.age = age;
        this.birthDate = birthDate;
    }
}
