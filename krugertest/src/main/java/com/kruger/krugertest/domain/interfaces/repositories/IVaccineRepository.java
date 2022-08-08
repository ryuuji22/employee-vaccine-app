package com.kruger.krugertest.domain.interfaces.repositories;

import java.util.Optional;

import com.kruger.krugertest.domain.entities.Vaccine;

public interface IVaccineRepository {

    Optional<Vaccine> getByName(String name);
    
    Optional<Vaccine> getById(String id);


}
