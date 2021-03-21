package com.github.hugovallada.tratamentoexcecoes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.hugovallada.tratamentoexcecoes.entity.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

    Optional<Animal> findByName(String name);

}
