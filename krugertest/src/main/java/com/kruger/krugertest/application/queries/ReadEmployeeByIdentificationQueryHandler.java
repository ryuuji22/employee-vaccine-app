package com.kruger.krugertest.application.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.kruger.krugertest.domain.entities.Employee;
import com.kruger.krugertest.domain.exceptions.ApplicationDomainException;
import com.kruger.krugertest.domain.interfaces.repositories.IEmployeeRepository;

import io.jkratz.mediator.core.RequestHandler;

@Component
public class ReadEmployeeByIdentificationQueryHandler
		implements RequestHandler<ReadEmployeeByIdentificationQuery, Employee> {

	@Autowired
	private IEmployeeRepository employeeRepository;

	@Override
	public Employee handle(ReadEmployeeByIdentificationQuery query) {
		String identification = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return employeeRepository.getByIdentification(identification)
				.orElseThrow(() -> new ApplicationDomainException(
				"Employee with identification " + identification + " doesn't exist."));
	}

}
