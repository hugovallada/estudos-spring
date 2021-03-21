package com.github.hugovallada.datatransferobjects.service;

import com.github.hugovallada.datatransferobjects.dtos.aluno.AlunoRequestDTO;
import com.github.hugovallada.datatransferobjects.dtos.aluno.AlunoResponseDTO;
import com.github.hugovallada.datatransferobjects.entity.Aluno;
import com.github.hugovallada.datatransferobjects.exception.custom.RegraNegocioException;
import com.github.hugovallada.datatransferobjects.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;

    @Autowired
    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public List<AlunoResponseDTO> buscarTodos() {
        return alunoRepository.findAll()
                .stream()
                .map(AlunoResponseDTO::toDto)
                .collect(Collectors.toList());
    }

    public AlunoResponseDTO criarAluno(AlunoRequestDTO aluno) {
        validaIdade(aluno);
        Aluno alunoSalvo = alunoRepository.save(aluno.toModel());
        return AlunoResponseDTO.toDto(alunoSalvo);
    }

    private void validaIdade(AlunoRequestDTO aluno) {
        int age = Period.between(aluno.getBirthDate(), LocalDate.now()).getYears();
        if (age < 18 || age >= 120) {
            throw new RegraNegocioException("A idade passada está fora dos limites aceitáveis");
        }
    }
}
