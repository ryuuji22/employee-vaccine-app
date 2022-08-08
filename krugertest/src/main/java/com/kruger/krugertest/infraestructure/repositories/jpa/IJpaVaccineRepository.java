/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kruger.krugertest.infraestructure.repositories.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kruger.krugertest.infraestructure.models.VaccineModel;

/**
 *
 * @author diego
 */
public interface IJpaVaccineRepository extends JpaRepository<VaccineModel, String> {
    public Optional<VaccineModel> findByNameIgnoreCase(String name);

}
