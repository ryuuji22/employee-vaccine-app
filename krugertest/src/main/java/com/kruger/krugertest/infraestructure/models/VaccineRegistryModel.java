/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kruger.krugertest.infraestructure.models;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.springframework.data.annotation.CreatedDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author diego
 */
@Entity
@Table(name = "VACCINE_REGISTRY", schema = "public", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "vaccine_id", "employee_id", "dose" }) })
@Getter
@Setter
@NoArgsConstructor
public class VaccineRegistryModel {

	@Id
	private String id;

	@Column(columnDefinition = "DATE")
	private LocalDate vaccinationDate;

	private Integer dose;

	@ManyToOne
	@JoinColumn(name = "employee_id", referencedColumnName = "id")
	private EmployeeModel employee;

	@ManyToOne
	@JoinColumn(name = "vaccine_id", referencedColumnName = "id")
	private VaccineModel vaccine;

	private Boolean enabled;

	@CreatedDate
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@PrePersist
	public void prePersist() {
		id = UUID.randomUUID().toString();
		createdAt = new Date();
		this.enabled = Boolean.TRUE;
	}

}
