package com.github.hugovallada.documentacao.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Carro{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String marca;
	
	private Integer ano;
	
	private Integer numeroPassageiros;
	
	private Double kmRodados;
	
	private Integer numDonos;

	public Carro(String marca, Integer ano, Integer numeroPassageiros, Double kmRodados, Integer numDonos) {
		super();
		this.marca = marca;
		this.ano = ano;
		this.numeroPassageiros = numeroPassageiros;
		this.kmRodados = kmRodados;
		this.numDonos = numDonos;
	}
	
	
	
}
