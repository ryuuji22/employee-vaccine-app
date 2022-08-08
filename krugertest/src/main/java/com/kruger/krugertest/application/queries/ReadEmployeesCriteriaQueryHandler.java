package com.kruger.krugertest.application.queries;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kruger.krugertest.domain.entities.Employee;
import com.kruger.krugertest.domain.interfaces.repositories.IEmployeeRepository;

import io.jkratz.mediator.core.RequestHandler;

@Component
public class ReadEmployeesCriteriaQueryHandler implements RequestHandler<ReadEmployeesCriteriaQuery, List<Employee>> {

	@Autowired
	private IEmployeeRepository employeeRepository;

	@Override
	public List<Employee> handle(ReadEmployeesCriteriaQuery query) {
		return employeeRepository.findEmployeesByVaccineStatusAndVaccineNameAndVaccinationDate(query.getVaccinated(),
				query.getVaccineName(), query.getBeforeDate(), query.getAfterDate());
	}

}
