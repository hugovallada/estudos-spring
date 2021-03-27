package com.github.hugovallada.documentacao.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.github.hugovallada.documentacao.entity.Carro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "Dados Recebidos sobre o Carro")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarroRequestDTO {
	
	@ApiModelProperty(value = "Marca do carro", required = true)
	@NotBlank(message = "A marca não pode ser nula")
	@Size(min = 3, max=20, message = "A marca deve ter entre 3 e 20 caracteres")
	private String marca;
	
	@ApiModelProperty(value = "Ano de fabricação do carro", required = true)
	@NotNull(message = "O ano não pode ser nulo")
	@Positive(message = "O ano não é válido")
	private Integer ano;
	
	@ApiModelProperty(value = "Número de passageiros que cabem no carro", required = true )
	@NotNull(message = "O número de passageiros não pode ser nulo")
	@Positive(message = "O número de passageiros não é válido")
	private Integer numeroPassageiros;
	
	@ApiModelProperty(value = "Número de quilômetros rodados pelo carro", required = true)
	@NotNull(message = "O número de km rodados não pode ser nulo")
	@Positive(message = "O número de km rodados não é valido")
	private Double kmRodados;
	
	@ApiModelProperty(value = "Número de donos que o carro já teve", required = true)
	@NotNull(message = "O número de donos não pode ser nulo")
	@Positive(message = "O número de donos é inválido")
	private Integer numDonos;

	
	public Carro toModel() {
		return new Carro(marca, ano, numeroPassageiros, kmRodados, numDonos);
	}

}
