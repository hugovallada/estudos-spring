package com.github.hugovallada.tratamentoexcecoes.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;


import lombok.Data;

@Entity
@Data
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "O nome não pode ser nulo ou estar em branco")
    @Length(min = 3, max = 20, message = "O nome deve ter entre 3 e 20 caracteres")
    private String name;

    @NotNull(message = "A idade não pode ser nula")
    @Positive(message = "A idade deve ser um valor positivo")
    private Integer age;

    @NotNull(message = "O número de pernas não pode ser nulo")
    @Positive(message = "O número de pernas deve ser um valor positivo")
    private Integer numberOfLegs;

}
