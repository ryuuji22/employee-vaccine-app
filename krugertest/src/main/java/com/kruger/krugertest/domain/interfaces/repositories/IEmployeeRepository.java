package com.kruger.krugertest.domain.interfaces.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.kruger.krugertest.domain.entities.Employee;

public interface IEmployeeRepository {

	Optional<List<Employee>> getAll();

	Optional<Employee> getByIdentification(String identification);

	String create(Employee employee);

	void delete(Employee employee);

	void update(Employee employee);

	List<Employee> findEmployeesByVaccineStatusAndVaccineNameAndVaccinationDate(Boolean vaccinated,
			String vaccineName, LocalDate dateBefore, LocalDate dateAfter);
}
