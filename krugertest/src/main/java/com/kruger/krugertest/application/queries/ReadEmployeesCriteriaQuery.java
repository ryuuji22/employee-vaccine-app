package com.kruger.krugertest.application.queries;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kruger.krugertest.domain.entities.Employee;

import io.jkratz.mediator.core.Request;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReadEmployeesCriteriaQuery implements Request<List<Employee>> {
	private Boolean vaccinated;
	private String vaccineName;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es_EC")
	private LocalDate beforeDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es_EC")
	private LocalDate afterDate;

}
