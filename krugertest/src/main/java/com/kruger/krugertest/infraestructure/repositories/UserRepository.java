/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kruger.krugertest.infraestructure.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kruger.krugertest.domain.entities.User;
import com.kruger.krugertest.domain.interfaces.repositories.IUserRepository;
import com.kruger.krugertest.infraestructure.models.UserModel;
import com.kruger.krugertest.infraestructure.persistance.mappers.UserMapper;
import com.kruger.krugertest.infraestructure.repositories.jpa.IJpaUserRepository;

/**
 *
 * @author diego
 */
@Repository
public class UserRepository implements IUserRepository {

    @Autowired
    private IJpaUserRepository jpaUserRepository;

    @Autowired
    private UserMapper mapper;

    @Override
    public Optional<List<User>> getAll() {
        return jpaUserRepository.findByEnabled(Boolean.TRUE)
                .map(mapper::toUsers);
    }

    @Override
    public Optional<User> getByIdentification(String identification) {
        return jpaUserRepository.findByIdentificationAndEnabled(identification, Boolean.TRUE)
                .map(mapper::toUser);
    }

    @Override
    public User create(User user) {
        UserModel userModel = mapper.toUserModel(user);
        return mapper.toUser(jpaUserRepository.save(userModel));
    }

    @Override
    public void delete(User user) {
        user.setEnabled(Boolean.FALSE);
        this.jpaUserRepository.save(mapper.toUserModel(user));
    }
    
    @Override
	public void update(User user) {
		this.jpaUserRepository.save(mapper.toUserModel(user));
	}

}
