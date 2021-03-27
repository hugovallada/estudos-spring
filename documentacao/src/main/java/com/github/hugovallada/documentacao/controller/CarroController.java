package com.github.hugovallada.documentacao.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.hugovallada.documentacao.dto.CarroRequestDTO;
import com.github.hugovallada.documentacao.dto.CarroResponseDTO;
import com.github.hugovallada.documentacao.service.CarroService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Carro")
@RestController
@RequestMapping("/carro")
public class CarroController {

	private final CarroService carroService;

	@Autowired
	public CarroController(CarroService carroService) {
		this.carroService = carroService;
	}

	@ApiOperation(value = "Listar Carros", nickname = "listarTodosCarros")
	@GetMapping
	public List<CarroResponseDTO> buscarTodos() {
		return carroService.buscarTodos();
	}

	@ApiOperation(value = "Criar novo Carro", nickname = "criarNovoCarro")
	@ApiResponses(@ApiResponse(code = 400, message = "Bad Request"))
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public CarroResponseDTO criar(@RequestBody @Valid CarroRequestDTO carroRequest) {
		return carroService.criar(carroRequest);
	}
}
