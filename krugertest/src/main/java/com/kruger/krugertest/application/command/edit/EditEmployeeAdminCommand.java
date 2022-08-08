package com.kruger.krugertest.application.command.edit;

import com.kruger.krugertest.application.dtos.requests.EmployeeBaseRequest;

import io.jkratz.mediator.core.Command;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EditEmployeeAdminCommand extends EmployeeBaseRequest implements Command {

	
}
