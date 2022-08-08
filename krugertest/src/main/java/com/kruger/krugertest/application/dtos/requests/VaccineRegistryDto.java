package com.kruger.krugertest.application.dtos.requests;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VaccineRegistryDto {
	
	@NotBlank(message = "Vaccine name can't be empty.")
	private String name;

	@NotNull(message = "Vaccine dose can't be null")
	@Min(value = 1,message = "Min dose value must be 1")
	private Integer dose;

	@NotNull(message = "Vaccinantion date can't be null")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es_EC")
	private LocalDate vaccionationDate;


}
