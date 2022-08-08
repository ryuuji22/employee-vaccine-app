/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kruger.krugertest.domain.entities;

import java.time.LocalDate;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author diego
 */
@Setter
@Getter
public class Employee {

	private String id;

	private String identification;

	private String firstName;

	private String lastName;

	private String email;

	private String address;

	private String phone;

	private LocalDate birthday;

	private Boolean vaccinated;

	@JsonIgnore
	private Boolean enabled;
	@JsonIgnore
	private User user;
	
	private Collection<VaccineRegistry> registry;

}
