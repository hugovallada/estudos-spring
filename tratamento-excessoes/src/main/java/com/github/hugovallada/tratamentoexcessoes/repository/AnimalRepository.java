package com.github.hugovallada.tratamentoexcessoes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.hugovallada.tratamentoexcessoes.entity.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
	
	Optional<Animal> findByName(String name);

}
