package com.github.hugovallada.documentacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.hugovallada.documentacao.repository.CarroRepository;

@Service
public class CarroService {

	private final CarroRepository carroRepository;
	
	@Autowired
	public CarroService (CarroRepository carroRepository) {
		this.carroRepository = carroRepository;
	}
	
	
}
