package com.kruger.krugertest.domain.interfaces.repositories;

import java.util.Optional;

import com.kruger.krugertest.domain.entities.Role;

public interface IRoleRepository {

    Optional<Role> getByName(String name);

	void create(Role role);


}
