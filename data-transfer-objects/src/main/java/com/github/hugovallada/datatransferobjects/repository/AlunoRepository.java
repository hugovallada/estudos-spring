package com.github.hugovallada.datatransferobjects.repository;

import com.github.hugovallada.datatransferobjects.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
