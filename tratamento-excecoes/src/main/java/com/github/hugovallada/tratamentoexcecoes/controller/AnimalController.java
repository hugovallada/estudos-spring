package com.github.hugovallada.tratamentoexcecoes.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.hugovallada.tratamentoexcecoes.entity.Animal;
import com.github.hugovallada.tratamentoexcecoes.service.AnimalService;
import com.github.hugovallada.tratamentoexcecoes.validators.ProibeNomeDuplicado;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    private final AnimalService animalService;
    
    @Autowired
    private ProibeNomeDuplicado proibeNomeDuplicado;

    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }
    
    @InitBinder
    public void init(WebDataBinder binder) {
    	binder.addValidators(proibeNomeDuplicado);
    }

    @GetMapping
    public List<Animal> listarTodosAnimais() {
        return animalService.listarTodos();
    }

    @GetMapping("/{id}")
    public Animal listarPorId(@PathVariable Long id) {
        return animalService.listarPorId(id);
    }

    @PostMapping
    public Animal criarAnimal(@RequestBody @Valid Animal animal) {
        return animalService.criarNovo(animal);
    }

}
