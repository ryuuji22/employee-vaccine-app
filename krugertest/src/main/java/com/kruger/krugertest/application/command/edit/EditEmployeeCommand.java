package com.kruger.krugertest.application.command.edit;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kruger.krugertest.application.dtos.requests.EmployeeBaseRequest;
import com.kruger.krugertest.application.dtos.requests.VaccineRegistryDto;

import io.jkratz.mediator.core.Command;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EditEmployeeCommand extends EmployeeBaseRequest implements Command {

	private String address;

	private String phone;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es_EC")
	private LocalDate birthday;

	private Boolean vaccinated;
	
	private List<@Valid VaccineRegistryDto> registry;



}
