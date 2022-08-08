package com.kruger.krugertest.application.queries;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kruger.krugertest.domain.entities.Employee;
import com.kruger.krugertest.domain.interfaces.repositories.IEmployeeRepository;

import io.jkratz.mediator.core.RequestHandler;

@Component
public class ReadEmployeesQueryHandler implements RequestHandler<ReadEmployeesQuery, List<Employee>> {

	@Autowired
    private IEmployeeRepository employeeRepository;

    @Override
    public List<Employee> handle(ReadEmployeesQuery query) {
         return employeeRepository.getAll().orElse(new ArrayList<>());
    }

}
