package com.github.hugovallada.tratamentoexcessoes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.hugovallada.tratamentoexcessoes.entity.Animal;
import com.github.hugovallada.tratamentoexcessoes.exception.custom.EntityAlreadyExistException;
import com.github.hugovallada.tratamentoexcessoes.repository.AnimalRepository;

@Service
public class AnimalService {
	
	private AnimalRepository animalRepository;
	
	@Autowired
	public AnimalService(AnimalRepository animalRepository) {
		this.animalRepository = animalRepository;
	}
	
	public List<Animal> listarTodos(){
		return animalRepository.findAll();
	}
	
	public Animal listarPorId(Long id) {
		Optional<Animal> animalOpt = animalRepository.findById(id);
		return animalOpt.get();
	}
	
	public Animal criarNovo(Animal animal) {
		validaAnimalExistByName(animal.getName());
		return animalRepository.save(animal);
	}
	
	private void validaAnimalExistByName(String name) {
		Optional<Animal> animalOpt = animalRepository.findByName(name);
		
		if(animalOpt.isPresent()) {
			throw new EntityAlreadyExistException(String.format("Já existe um animal com nome %s no banco",name));
		}
	}

}
