package com.kruger.krugertest.application.command.delete;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kruger.krugertest.domain.entities.Employee;
import com.kruger.krugertest.domain.exceptions.ApplicationDomainException;
import com.kruger.krugertest.domain.interfaces.repositories.IEmployeeRepository;
import com.kruger.krugertest.domain.interfaces.repositories.IUserRepository;

import io.jkratz.mediator.core.CommandHandler;

@Component
public class DeleteEmployeeCommandHandler implements CommandHandler<DeleteEmployeeCommand> {
	@Autowired
	private IEmployeeRepository employeeRepository;
	@Autowired
	private IUserRepository userRepository;

	@Override
	@Transactional
	public void handle(DeleteEmployeeCommand deleteEmployeeCommand) {

		Employee employeeDb = this.validateEmployee(deleteEmployeeCommand.getIdentification());
		this.employeeRepository.delete(employeeDb);
		this.userRepository.delete(employeeDb.getUser());
		

	}

	private Employee validateEmployee(String identification) {
		Optional<Employee> employeeFound = employeeRepository.getByIdentification(identification);
		if (!employeeFound.isPresent()) {
			throw new ApplicationDomainException("Employee for identification " + identification + " doesn't exist");
		}
		return employeeFound.get();

	}
}
