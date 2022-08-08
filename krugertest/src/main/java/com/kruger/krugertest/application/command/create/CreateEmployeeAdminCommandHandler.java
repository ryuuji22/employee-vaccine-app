package com.kruger.krugertest.application.command.create;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kruger.krugertest.domain.entities.Employee;
import com.kruger.krugertest.domain.entities.User;
import com.kruger.krugertest.domain.exceptions.ApplicationDomainException;
import com.kruger.krugertest.domain.interfaces.repositories.IEmployeeRepository;
import com.kruger.krugertest.domain.services.create.CreateUserService;
import com.kruger.krugertest.domain.services.validations.ValidateIdentificationCIService;

import io.jkratz.mediator.core.CommandHandler;
import io.jkratz.mediator.core.Mediator;

@Component
public class CreateEmployeeAdminCommandHandler implements CommandHandler<CreateEmployeeAdminCommand> {

	@Autowired
	private IEmployeeRepository employeeRepository;
	@Autowired
	private Mediator mediator;

	@Override
	@Transactional
	public void handle(CreateEmployeeAdminCommand command) {

		Boolean isValid = this.mediator.dispatch(new ValidateIdentificationCIService(command.getIdentification()));

		if (Boolean.FALSE.equals(isValid)) {
			throw new ApplicationDomainException("Identification " + command.getIdentification() + " is not valid");
		}

		this.validateEmployee(command.getIdentification());

		CreateUserService createUserservice = new CreateUserService();
		createUserservice.setIdentification(command.getIdentification());
		createUserservice.setRoles(command.getRoles());
		User user = this.mediator.dispatch(createUserservice);

		Employee newEmployee = new Employee();
		newEmployee.setIdentification(command.getIdentification());
		newEmployee.setFirstName(command.getFirstName());
		newEmployee.setLastName(command.getLastName());
		newEmployee.setEmail(command.getEmail());
		newEmployee.setUser(user);

		employeeRepository.create(newEmployee);

	}

	private void validateEmployee(String identification) {
		Optional<Employee> employeeFound = employeeRepository.getByIdentification(identification);
		if (employeeFound.isPresent()) {
			throw new ApplicationDomainException("Employee already exists for identification " + identification);
		}

	}
}
