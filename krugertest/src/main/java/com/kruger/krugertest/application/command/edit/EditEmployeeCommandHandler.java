package com.kruger.krugertest.application.command.edit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kruger.krugertest.application.dtos.requests.VaccineRegistryDto;
import com.kruger.krugertest.domain.entities.Employee;
import com.kruger.krugertest.domain.entities.Vaccine;
import com.kruger.krugertest.domain.entities.VaccineRegistry;
import com.kruger.krugertest.domain.exceptions.ApplicationDomainException;
import com.kruger.krugertest.domain.interfaces.repositories.IEmployeeRepository;
import com.kruger.krugertest.domain.interfaces.repositories.IVaccineRegistryRepository;
import com.kruger.krugertest.domain.interfaces.repositories.IVaccineRepository;

import io.jkratz.mediator.core.CommandHandler;

@Component
public class EditEmployeeCommandHandler implements CommandHandler<EditEmployeeCommand> {

	@Autowired
	private IEmployeeRepository employeeRepository;
	@Autowired
	private IVaccineRepository vaccineRepository;
	@Autowired
	private IVaccineRegistryRepository vaccineRegistryRepository;

	@Override
	@Transactional
	public void handle(EditEmployeeCommand command) {

		Employee employeeDb = this.validateEmployee(command.getIdentification());
		List<VaccineRegistry> registries = this.validateVaccineRegistry(command.getIdentification());
		this.createVaccineRegistry(command, employeeDb, registries);
		this.updateEmployee(command, employeeDb);

	}

	private Vaccine validateVaccine(String name) {
		Optional<Vaccine> vaccineFound = vaccineRepository.getByName(name);
		if (!vaccineFound.isPresent()) {
			throw new ApplicationDomainException("Vaccine " + name + " doesn't exist");
		}
		return vaccineFound.get();

	}

	private Employee validateEmployee(String identification) {
		Optional<Employee> employeeFound = employeeRepository.getByIdentification(identification);
		if (!employeeFound.isPresent()) {
			throw new ApplicationDomainException("Employee for identification " + identification + " doesn't exist");
		}
		return employeeFound.get();

	}

	private List<VaccineRegistry> validateVaccineRegistry(String identification) {
		Optional<List<VaccineRegistry>> vaccineRegistries = vaccineRegistryRepository
				.getByEmployeeIdentification(identification);
		return vaccineRegistries.orElse(new ArrayList<>());

	}

	private void validateUniqueVaccineRegistry(String identification, String name, Integer dose) {
		Optional<VaccineRegistry> vaccineRegistry = vaccineRegistryRepository.findUniqueVaccinationDose(identification,
				name, dose);
		if (vaccineRegistry.isPresent()) {
			throw new ApplicationDomainException(
					"You already have a vaccine registry for: " + name + " with dose: " + dose);
		}

	}

	private void updateEmployee(EditEmployeeCommand command, Employee employeeDb) {
		employeeDb.setIdentification(command.getIdentification());
		employeeDb.setFirstName(command.getFirstName());
		employeeDb.setLastName(command.getLastName());
		employeeDb.setEmail(command.getEmail());
		employeeDb.setAddress(command.getAddress());
		employeeDb.setBirthday(command.getBirthday());
		employeeDb.setPhone(command.getPhone());
		employeeDb.setVaccinated(command.getVaccinated());
		employeeRepository.update(employeeDb);

	}

	private void createVaccineRegistry(EditEmployeeCommand command, Employee employeeDb,
			List<VaccineRegistry> registries) {

		if (command.getVaccinated() != null) {

			if (!registries.isEmpty() && !command.getVaccinated()) {
				throw new ApplicationDomainException("Can't change your status to NO vaccinated because you already "
						+ "have an active registry of vaccines.");
			}
			if (registries.isEmpty() && command.getVaccinated()
					&& (command.getRegistry() == null || command.getRegistry().isEmpty())) {
				throw new ApplicationDomainException("If vaccinated flag is TRUE "
						+ "you must provide a registry of vaccines or already have an active registry stored.");
			}

			if (command.getVaccinated() && (command.getRegistry() != null && !command.getRegistry().isEmpty())) {
				for (VaccineRegistryDto registryDto : command.getRegistry()) {
					Vaccine vaccineDb = this.validateVaccine(registryDto.getName());
					this.validateUniqueVaccineRegistry(command.getIdentification(), registryDto.getName(),
							registryDto.getDose());
					VaccineRegistry newRegistry = new VaccineRegistry();
					newRegistry.setEmployee(employeeDb);
					newRegistry.setVaccine(vaccineDb);
					newRegistry.setDose(registryDto.getDose());
					newRegistry.setVaccinationDate(registryDto.getVaccionationDate());
					vaccineRegistryRepository.create(newRegistry);
				}
			}
		} else {
			if (!registries.isEmpty()) {
				throw new ApplicationDomainException("Can't change your status to NULL vaccinated because you already "
						+ "have an active registry of vaccines.");
			}
		}
	}
}
