package com.kruger.krugertest.application.command.delete;


import javax.validation.constraints.NotBlank;

import io.jkratz.mediator.core.Command;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeleteEmployeeCommand implements Command {

	@NotBlank(message = "Identification field can't be empty.")
	private String identification;

}

