package com.github.hugovallada.documentacao.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.github.hugovallada.documentacao.entity.Carro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarroRequestDTO {
	
	@NotBlank(message = "A marca não pode ser nula")
	@Size(min = 3, max=20, message = "A marca deve ter entre 3 e 20 caracteres")
	private String marca;
	
	@NotNull(message = "O ano não pode ser nulo")
	@Positive(message = "O ano não é válido")
	private Integer ano;
	
	@NotNull(message = "O número de passageiros não pode ser nulo")
	@Positive(message = "O número de passageiros não é válido")
	private Integer numeroPassageiros;
	
	@NotNull(message = "O número de km rodados não pode ser nulo")
	@Positive(message = "O número de km rodados não é valido")
	private Double kmRodados;
	
	@NotNull(message = "O número de donos não pode ser nulo")
	@Positive(message = "O número de donos é inválido")
	private Integer numDonos;

	
	public Carro toModel() {
		return new Carro(marca, ano, numeroPassageiros, kmRodados, numDonos);
	}

}
