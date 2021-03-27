package com.github.hugovallada.documentacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.hugovallada.documentacao.service.CarroService;

@RestController
@RequestMapping("/carro")
public class CarroController {
	
	private final CarroService carroService;
	
	@Autowired
	public CarroController(CarroService carroService) {
		this.carroService = carroService;
	}
}
