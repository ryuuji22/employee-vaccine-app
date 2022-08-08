package com.kruger.krugertest.domain.interfaces.repositories;

import java.util.List;
import java.util.Optional;

import com.kruger.krugertest.domain.entities.VaccineRegistry;

public interface IVaccineRegistryRepository {

    Optional<List<VaccineRegistry>> getByEmployeeIdentification(String identification);
    
    Optional<VaccineRegistry> findUniqueVaccinationDose(String identification, String name, Integer dose);

    String create(VaccineRegistry vaccineRegistry);

    void delete(VaccineRegistry vaccineRegistry);


}
