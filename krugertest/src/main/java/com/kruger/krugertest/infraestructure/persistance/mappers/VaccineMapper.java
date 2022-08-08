package com.kruger.krugertest.infraestructure.persistance.mappers;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kruger.krugertest.domain.entities.Vaccine;
import com.kruger.krugertest.infraestructure.models.VaccineModel;

@Mapper(componentModel = "spring")
public interface VaccineMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    Vaccine toVaccine(VaccineModel vaccineModel);

    List<Vaccine> toVaccines(List<VaccineModel> vaccines);

    @InheritInverseConfiguration
    VaccineModel toVaccineModel(Vaccine vaccines);
}
