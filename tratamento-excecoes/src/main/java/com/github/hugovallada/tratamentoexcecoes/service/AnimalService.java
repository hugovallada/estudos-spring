package com.github.hugovallada.tratamentoexcecoes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.hugovallada.tratamentoexcecoes.entity.Animal;
import com.github.hugovallada.tratamentoexcecoes.exception.custom.EntityAlreadyExistException;
import com.github.hugovallada.tratamentoexcecoes.exception.custom.RegraNegocioException;
import com.github.hugovallada.tratamentoexcecoes.repository.AnimalRepository;

@Service
public class AnimalService {

    private AnimalRepository animalRepository;

    @Autowired
    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> listarTodos() {
        return animalRepository.findAll();
    }

    public Animal listarPorId(Long id) {
        Optional<Animal> animalOpt = animalRepository.findById(id);
        return animalOpt.get();
    }

    public Animal criarNovo(Animal animal) {
        validaAnimalExistByName(animal.getName());
        regraNegocio(animal);
        return animalRepository.save(animal);
    }

    private void validaAnimalExistByName(String name) {
        Optional<Animal> animalOpt = animalRepository.findByName(name);

        if (animalOpt.isPresent()) {
            throw new EntityAlreadyExistException(String.format("Já existe um animal com nome %s no banco", name));
        }
    }

    private void regraNegocio(Animal animal) {
        if (animal.getNumberOfLegs() > 4) {
            throw new RegraNegocioException("Para ser classificado nessa base, o animal não pode ter mais que 4 pernas");
        }
    }

}
