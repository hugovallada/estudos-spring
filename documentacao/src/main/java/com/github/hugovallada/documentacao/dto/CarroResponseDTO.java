package com.github.hugovallada.documentacao.dto;

import com.github.hugovallada.documentacao.entity.Carro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarroResponseDTO {
	
	private Long id;
	
	private String marca;
	
	private Integer ano;
	
	private Integer numeroPassageiros;
	
	private Double kmRodados;
	
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
