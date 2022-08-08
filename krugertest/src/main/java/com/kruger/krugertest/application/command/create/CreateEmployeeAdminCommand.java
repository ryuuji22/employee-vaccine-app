package com.kruger.krugertest.application.command.create;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.kruger.krugertest.application.dtos.requests.EmployeeBaseRequest;

import io.jkratz.mediator.core.Command;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateEmployeeAdminCommand extends EmployeeBaseRequest implements Command {

	@NotEmpty(message = "Roles array can't be empty.")
	private List<String> roles;

}
