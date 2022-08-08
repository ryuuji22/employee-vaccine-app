package com.kruger.krugertest.infraestructure.persistance.mappers;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kruger.krugertest.domain.entities.VaccineRegistry;
import com.kruger.krugertest.infraestructure.models.VaccineRegistryModel;

@Mapper(componentModel = "spring", uses = { VaccineMapper.class })
public interface VaccineRegistryMapper {

	@Mapping(source = "id", target = "id")
	@Mapping(source = "vaccinationDate", target = "vaccinationDate")
	@Mapping(source = "dose", target = "dose")
	@Mapping(source = "employee.id", target = "employee.id")
	@Mapping(source = "vaccine", target = "vaccine")
	@Mapping(source = "enabled", target = "enabled")
	VaccineRegistry toVaccineRegistry(VaccineRegistryModel vaccineRegistryModel);

	List<VaccineRegistry> toVaccineRegistries(List<VaccineRegistryModel> vaccineRegistries);

	@InheritInverseConfiguration
	VaccineRegistryModel toVaccineRegistryModel(VaccineRegistry vaccineRegistry);
}
