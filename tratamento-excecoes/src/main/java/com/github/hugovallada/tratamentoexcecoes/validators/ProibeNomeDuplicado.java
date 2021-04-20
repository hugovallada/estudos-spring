package com.github.hugovallada.tratamentoexcecoes.validators;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.github.hugovallada.tratamentoexcecoes.dto.AnimalDto;
import com.github.hugovallada.tratamentoexcecoes.entity.Animal;
import com.github.hugovallada.tratamentoexcecoes.repository.AnimalRepository;

public class ProibeNomeDuplicado implements Validator {
	
	@Autowired
	private AnimalRepository animalRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return AnimalDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) {
			return ;
		}
		
		
		AnimalDto request = (AnimalDto) target;
		Optional<Animal> animalOpt = animalRepository.findByName(request.getName());
		
		if(animalOpt.isPresent()) {
			errors.rejectValue("name", null, "Já existe um animal com esse nome");
		}
	}

}
