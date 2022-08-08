/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kruger.krugertest.domain.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author diego
 */
@Getter
@Setter
public class VaccineRegistry {

	private String id;
	private LocalDate vaccinationDate;
	private Integer dose;
	@JsonIgnore
	private Employee employee;
	private Vaccine vaccine;
	private Boolean enabled;

}
