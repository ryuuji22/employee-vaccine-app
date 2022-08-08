package com.kruger.krugertest.application.command.edit;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kruger.krugertest.domain.entities.Employee;
import com.kruger.krugertest.domain.entities.User;
import com.kruger.krugertest.domain.exceptions.ApplicationDomainException;
import com.kruger.krugertest.domain.interfaces.repositories.IEmployeeRepository;
import com.kruger.krugertest.domain.interfaces.repositories.IUserRepository;
import com.kruger.krugertest.domain.services.validations.ValidateIdentificationCIService;

import io.jkratz.mediator.core.CommandHandler;
import io.jkratz.mediator.core.Mediator;

@Component
public class EditEmployeeAdminCommandHandler implements CommandHandler<EditEmployeeAdminCommand> {

	@Autowired
	private IEmployeeRepository employeeRepository;
	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private Mediator mediator;

	@Override
	@Transactional
	public void handle(EditEmployeeAdminCommand command) {

		Employee employeeDb = this.validateEmployee(command.getIdentification());
		User userDb = this.validateUser(command.getIdentification());
		
		Boolean isValid = this.mediator.dispatch(new ValidateIdentificationCIService(command.getIdentification()));

		if (Boolean.FALSE.equals(isValid)) {
			throw new ApplicationDomainException("Identification " + command.getIdentification() + " is not valid");
		}
		
		userDb.setIdentification(command.getIdentification());

		employeeDb.setIdentification(command.getIdentification());
		employeeDb.setFirstName(command.getFirstName());
		employeeDb.setLastName(command.getLastName());
		employeeDb.setEmail(command.getEmail());

		employeeRepository.update(employeeDb);
		userRepository.update(userDb);

	}

	private Employee validateEmployee(String identification) {
		Optional<Employee> employeeFound = employeeRepository.getByIdentification(identification);
		if (!employeeFound.isPresent()) {
			throw new ApplicationDomainException("Employee for identification " + identification + " doesn't exist");
		}
		return employeeFound.get();

	}

	private User validateUser(String identification) {
		Optional<User> userFound = userRepository.getByIdentification(identification);
		if (!userFound.isPresent()) {
			throw new ApplicationDomainException("User for identification " + identification + " doesn't exist");
		}
		return userFound.get();

	}
}
