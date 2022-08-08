/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kruger.krugertest.infraestructure.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kruger.krugertest.domain.entities.Vaccine;
import com.kruger.krugertest.domain.interfaces.repositories.IVaccineRepository;
import com.kruger.krugertest.infraestructure.persistance.mappers.VaccineMapper;
import com.kruger.krugertest.infraestructure.repositories.jpa.IJpaVaccineRepository;

/**
 *
 * @author diego
 */
@Repository
public class VaccineRepository implements IVaccineRepository {

	@Autowired
	private IJpaVaccineRepository jpaVaccineRepository;

	@Autowired
	private VaccineMapper mapper;

	@Override
	public Optional<Vaccine> getByName(String name) {
		return jpaVaccineRepository.findByNameIgnoreCase(name).map(mapper::toVaccine);
	}

	@Override
	public Optional<Vaccine> getById(String id) {
		return jpaVaccineRepository.findById(id).map(mapper::toVaccine);
	}


}
