/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kruger.krugertest.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kruger.krugertest.application.command.create.CreateEmployeeAdminCommand;
import com.kruger.krugertest.application.command.delete.DeleteEmployeeCommand;
import com.kruger.krugertest.application.command.edit.EditEmployeeAdminCommand;
import com.kruger.krugertest.application.queries.ReadEmployeesCriteriaQuery;
import com.kruger.krugertest.application.queries.ReadEmployeesQuery;
import com.kruger.krugertest.domain.entities.Employee;

import io.jkratz.mediator.core.Mediator;

/**
 *
 * @author diego
 */
@RestController
@RequestMapping("/api/admin/employee")
public class EmployeeAdminController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeAdminController.class);

	@Autowired
	private Mediator mediator;

	@PostMapping
	public ResponseEntity<Void> createEmployee(@Valid @RequestBody CreateEmployeeAdminCommand command) {

		logger.info("------ Sending command: {} ", command.getClass().getName());

		this.mediator.dispatch(command);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PostMapping("/criteria")
	public ResponseEntity<List<Employee>> readEmployeesByCriteria(
			@Valid @RequestBody ReadEmployeesCriteriaQuery query) {

		logger.info("------ Sending command: {} ", query.getClass().getName());

		return new ResponseEntity<>(this.mediator.dispatch(query), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Employee>> readEmployees() {
		ReadEmployeesQuery query = new ReadEmployeesQuery();
		logger.info("----- Sending Query: {} ", query.getClass().getName());
		return new ResponseEntity<>(this.mediator.dispatch(query), HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteEmployee(@Valid @RequestBody DeleteEmployeeCommand deleteEmployeeCommand) {

		logger.info("--------- Sending command: {} ", deleteEmployeeCommand.getClass().getName());
		this.mediator.dispatch(deleteEmployeeCommand);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PatchMapping
	public ResponseEntity<Void> editEmployee(@Valid @RequestBody EditEmployeeAdminCommand command) {

		logger.info("------ Sending command: {} ", command.getClass().getName());

		this.mediator.dispatch(command);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
