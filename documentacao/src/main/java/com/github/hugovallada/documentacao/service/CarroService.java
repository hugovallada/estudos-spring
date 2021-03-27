package com.github.hugovallada.documentacao.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.hugovallada.documentacao.dto.CarroRequestDTO;
import com.github.hugovallada.documentacao.dto.CarroResponseDTO;
import com.github.hugovallada.documentacao.entity.Carro;
import com.github.hugovallada.documentacao.infra.exception.RegraNegocioException;
import com.github.hugovallada.documentacao.repository.CarroRepository;

@Service
public class CarroService {

	private final CarroRepository carroRepository;
	
	@Autowired
	public CarroService (CarroRepository carroRepository) {
		this.carroRepository = carroRepository;
	}
	
	public List<CarroResponseDTO> buscarTodos(){
		List<Carro> carros = carroRepository.findAll();
		return carros.stream()
				.map(carro -> CarroResponseDTO.toDto(carro))
				.collect(Collectors.toList());
	}
	
	public CarroResponseDTO criar(CarroRequestDTO carroRequestDTO) {
		
		if(carroRequestDTO.getAno() < 1920 || carroRequestDTO.getAno() > 2021) {
			throw new RegraNegocioException("O ano é inválido");
		}
		
		Carro carro = carroRepository.save(carroRequestDTO.toModel());
		return CarroResponseDTO.toDto(carro);
	}
	
	
}
