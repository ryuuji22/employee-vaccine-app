/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kruger.krugertest.infraestructure.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kruger.krugertest.domain.entities.VaccineRegistry;
import com.kruger.krugertest.domain.interfaces.repositories.IVaccineRegistryRepository;
import com.kruger.krugertest.infraestructure.models.VaccineRegistryModel;
import com.kruger.krugertest.infraestructure.persistance.mappers.VaccineRegistryMapper;
import com.kruger.krugertest.infraestructure.repositories.jpa.IJpaVaccineRegistryRepository;

/**
 *
 * @author diego
 */
@Repository
public class VaccineRegistryRepository implements IVaccineRegistryRepository {

	@Autowired
	private IJpaVaccineRegistryRepository jpaVaccineRegistryRepository;

	@Autowired
	private VaccineRegistryMapper mapper;

	@Override
	public Optional<VaccineRegistry> findUniqueVaccinationDose(String identification, String name, Integer dose) {
		return jpaVaccineRegistryRepository
				.findByEmployeeIdentificationAndVaccineNameAndDoseAndEnabled(identification, name, dose, Boolean.TRUE)
				.map(mapper::toVaccineRegistry);
	}

	@Override
	public String create(VaccineRegistry vaccineRegistry) {
		VaccineRegistryModel vaccineRegistryModel = mapper.toVaccineRegistryModel(vaccineRegistry);
		return jpaVaccineRegistryRepository.save(vaccineRegistryModel).getId();
	}

	@Override
	public void delete(VaccineRegistry vaccineRegistry) {
		vaccineRegistry.setEnabled(Boolean.FALSE);
		this.jpaVaccineRegistryRepository.save(mapper.toVaccineRegistryModel(vaccineRegistry));
	}

	@Override
	public Optional<List<VaccineRegistry>> getByEmployeeIdentification(String identification) {
		return this.jpaVaccineRegistryRepository.findByEmployeeIdentificationAndEnabled(identification, Boolean.TRUE)
				.map(mapper::toVaccineRegistries);
	}

}
