package com.github.hugovallada.datatransferobjects.controller;

import com.github.hugovallada.datatransferobjects.dtos.aluno.AlunoRequestDTO;
import com.github.hugovallada.datatransferobjects.dtos.aluno.AlunoResponseDTO;
import com.github.hugovallada.datatransferobjects.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    private final AlunoService alunoService;

    @Autowired
    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping
    public List<AlunoResponseDTO> buscarAlunos() {
        return alunoService.buscarTodos();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public AlunoResponseDTO criarAluno(@RequestBody AlunoRequestDTO aluno) {
        return alunoService.criarAluno(aluno);
    }

}
