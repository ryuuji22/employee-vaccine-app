/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kruger.krugertest.infraestructure.repositories.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kruger.krugertest.infraestructure.models.VaccineRegistryModel;

/**
 *
 * @author diego
 */
public interface IJpaVaccineRegistryRepository extends JpaRepository<VaccineRegistryModel, String> {
	public Optional<List<VaccineRegistryModel>> findByEmployeeIdentificationAndEnabled(String identification,
			Boolean enabled);

	public Optional<VaccineRegistryModel> findByEmployeeIdentificationAndVaccineNameAndDoseAndEnabled(
			String identification, String name, Integer dose, Boolean enabled);

}
