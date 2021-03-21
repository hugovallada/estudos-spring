package com.github.hugovallada.datatransferobjects.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String ra;

    private String password;

    private String login;

    private LocalDate birthDate;

    public Aluno(String name, String ra, String password, String login, LocalDate birthDate) {
        this.name = name;
        this.ra = ra;
        this.password = password;
        this.login = login;
        this.birthDate = birthDate;
    }
}


