/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kruger.krugertest.infraestructure.repositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kruger.krugertest.domain.entities.Employee;
import com.kruger.krugertest.domain.interfaces.repositories.IEmployeeRepository;
import com.kruger.krugertest.infraestructure.models.EmployeeModel;
import com.kruger.krugertest.infraestructure.models.VaccineModel;
import com.kruger.krugertest.infraestructure.models.VaccineRegistryModel;
import com.kruger.krugertest.infraestructure.persistance.mappers.EmployeeMapper;
import com.kruger.krugertest.infraestructure.repositories.jpa.IJpaEmployeeRepository;

/**
 *
 * @author diego
 */
@Repository
public class EmployeeRepository implements IEmployeeRepository {

	@Autowired
	private IJpaEmployeeRepository jpaEmployeeRepository;

	@Autowired
	private EmployeeMapper mapper;

	@Autowired
	private EntityManager entityManager;

	@Override
	public Optional<List<Employee>> getAll() {
		return jpaEmployeeRepository.findByEnabled(Boolean.TRUE).map(mapper::toEmployees);
	}

	@Override
	public String create(Employee employee) {
		EmployeeModel employeeModel = mapper.toEmployeeModel(employee);
		return jpaEmployeeRepository.save(employeeModel).getId();
	}

	@Override
	public void delete(Employee employee) {
		employee.setEnabled(Boolean.FALSE);
		this.jpaEmployeeRepository.save(mapper.toEmployeeModel(employee));
	}

	@Override
	public void update(Employee employee) {
		this.jpaEmployeeRepository.save(mapper.toEmployeeModel(employee));
	}

	@Override
	public Optional<Employee> getByIdentification(String identification) {
		return this.jpaEmployeeRepository.findByIdentificationAndEnabled(identification, Boolean.TRUE)
				.map(mapper::toEmployee);
	}

	private List<EmployeeModel> getEmployeeCriteriaQuery(Boolean vaccinated, String vaccineName, LocalDate dateBefore,
			LocalDate dateAfter) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<EmployeeModel> criteriaQuery = criteriaBuilder.createQuery(EmployeeModel.class);

		Root<EmployeeModel> root = criteriaQuery.from(EmployeeModel.class);
		Join<VaccineRegistryModel, EmployeeModel> joinRegistry = root.join("vaccineRegistry", JoinType.LEFT);
		Join<VaccineRegistryModel, VaccineModel> joinVaccine = joinRegistry.join("vaccine", JoinType.LEFT);

		List<Predicate> predicates = new ArrayList<>();

		criteriaQuery.select(root).distinct(true);

		Predicate enabledCondition = criteriaBuilder.isTrue(root.get("enabled"));
		predicates.add(enabledCondition);

		if (vaccinated != null) {
			Predicate vaccinatedCondition = criteriaBuilder.equal(root.get("vaccinated"), vaccinated);
			predicates.add(vaccinatedCondition);
		}

		if (vaccineName != null) {
			Predicate vaccineNameCondition = criteriaBuilder.like(criteriaBuilder.lower(joinVaccine.get("name")),
					"%" + vaccineName.toLowerCase() + "%");
			predicates.add(vaccineNameCondition);
		}

		if (dateBefore != null && dateAfter != null) {
			Predicate dateCondition = criteriaBuilder.between(joinRegistry.get("vaccinationDate"), dateBefore,
					dateAfter);
			predicates.add(dateCondition);
		}

		if (!predicates.isEmpty()) {
			predicates.forEach((p) -> {
				criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
			});
		}

		TypedQuery<EmployeeModel> query = entityManager.createQuery(criteriaQuery);
		
		System.out.println(query.toString());

		return query.getResultList();

	}

	@Override
	public List<Employee> findEmployeesByVaccineStatusAndVaccineNameAndVaccinationDate(Boolean vaccinated,
			String vaccineName, LocalDate dateBefore, LocalDate dateAfter) {

		return mapper.toEmployees(this.getEmployeeCriteriaQuery(vaccinated, vaccineName, dateBefore, dateAfter));
	}

}
