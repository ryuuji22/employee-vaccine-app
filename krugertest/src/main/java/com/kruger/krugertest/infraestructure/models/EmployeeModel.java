/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kruger.krugertest.infraestructure.models;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author diego
 */
@Entity
@Table(name = "EMPLOYEES", schema = "public")
@Getter
@Setter
@NoArgsConstructor
public class EmployeeModel {

	@Id
	private String id;

	@Column(nullable = false)
	private String identification;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private String email;

	private String address;

	private String phone;

	@Column(columnDefinition = "DATE")
	private LocalDate birthday;

	private Boolean vaccinated;

	private Boolean enabled;

	@CreatedDate
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private UserModel user;

	@OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
	private Collection<VaccineRegistryModel> vaccineRegistry;

	@PrePersist
	public void prePersist() {
		id = UUID.randomUUID().toString();
		createdAt = new Date();
		this.enabled = Boolean.TRUE;
	}

}
