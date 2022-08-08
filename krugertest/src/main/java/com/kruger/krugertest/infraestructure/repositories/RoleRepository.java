/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kruger.krugertest.infraestructure.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kruger.krugertest.domain.entities.Role;
import com.kruger.krugertest.domain.interfaces.repositories.IRoleRepository;
import com.kruger.krugertest.infraestructure.models.RoleModel;
import com.kruger.krugertest.infraestructure.persistance.mappers.RoleMapper;
import com.kruger.krugertest.infraestructure.repositories.jpa.IJpaRoleRepository;

/**
 *
 * @author diego
 */
@Repository
public class RoleRepository implements IRoleRepository {

	@Autowired
	private IJpaRoleRepository jpaRoleRepository;

	@Autowired
	private RoleMapper mapper;

	@Override
	public Optional<Role> getByName(String name) {
		return jpaRoleRepository.findByName(name).map(mapper::toRole);
	}

	@Override
	public void create(Role role) {
		RoleModel roleModel = mapper.toRoleModel(role);
		jpaRoleRepository.save(roleModel);
	}

}
