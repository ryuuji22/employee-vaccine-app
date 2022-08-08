package com.kruger.krugertest.infraestructure.persistance.mappers;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kruger.krugertest.domain.entities.Employee;
import com.kruger.krugertest.infraestructure.models.EmployeeModel;

@Mapper(componentModel = "spring", uses = { UserMapper.class, VaccineRegistryMapper.class })
public interface EmployeeMapper {

	@Mapping(source = "id", target = "id")
	@Mapping(source = "identification", target = "identification")
	@Mapping(source = "firstName", target = "firstName")
	@Mapping(source = "lastName", target = "lastName")
	@Mapping(source = "email", target = "email")
	@Mapping(source = "address", target = "address")
	@Mapping(source = "phone", target = "phone")
	@Mapping(source = "birthday", target = "birthday")
	@Mapping(source = "vaccinated", target = "vaccinated")
	@Mapping(source = "enabled", target = "enabled")
	@Mapping(source = "user", target = "user")
	@Mapping(source = "vaccineRegistry", target = "registry")
	Employee toEmployee(EmployeeModel employeeModel);

	List<Employee> toEmployees(List<EmployeeModel> employees);

	@InheritInverseConfiguration
	EmployeeModel toEmployeeModel(Employee employee);
}
