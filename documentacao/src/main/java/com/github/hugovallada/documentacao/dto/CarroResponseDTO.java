package com.github.hugovallada.documentacao.dto;

import com.github.hugovallada.documentacao.entity.Carro;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("Dados Retornados sobre o Carro")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarroResponseDTO {
	
	@ApiModelProperty(value = "Código de identificação do carro")
	private Long id;
	
	@ApiModelProperty(value = "Marca do carro")
	private String marca;
	
	@ApiModelProperty(value = "Ano de fabricação do carro")
	private Integer ano;
	
	@ApiModelProperty(value = "Número de passageiros que podem ser transportados no carro")
	private Integer numeroPassageiros;
	
	@ApiModelProperty(value = "Quantos quilômetros o carro já rodou")
	private Double kmRodados;
	
	@ApiModelProperty(value = "Quantos donos o carro já teve")
	private Integer numDonos;
	
	
	public static CarroResponseDTO toDto(Carro carro) {
		return new CarroResponseDTO(
				carro.getId(),
				carro.getMarca(),
				carro.getAno(),
				carro.getNumeroPassageiros(),
				carro.getKmRodados(),
				carro.getNumDonos());
	}



	

}
