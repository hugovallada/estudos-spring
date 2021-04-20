package com.github.hugovallada.tratamentoexcecoes.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

public class AnimalDto {

	private Long id;


	@NotBlank(message = "O nome não pode ser nulo ou estar em branco")
	@Length(min = 3, max = 20, message = "O nome deve ter entre 3 e 20 caracteres")
	private String name;

	@NotNull(message = "A idade não pode ser nula")
	@Positive(message = "A idade deve ser um valor positivo")
	private Integer age;

	@NotNull(message = "O número de pernas não pode ser nulo")
	@Positive(message = "O número de pernas deve ser um valor positivo")
	private Integer numberOfLegs;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getNumberOfLegs() {
		return numberOfLegs;
	}

	public void setNumberOfLegs(Integer numberOfLegs) {
		this.numberOfLegs = numberOfLegs;
	}
	
	
	


}
