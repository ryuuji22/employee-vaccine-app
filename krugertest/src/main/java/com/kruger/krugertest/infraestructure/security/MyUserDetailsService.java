/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kruger.krugertest.infraestructure.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.kruger.krugertest.domain.entities.Role;
import com.kruger.krugertest.domain.entities.User;
import com.kruger.krugertest.domain.exceptions.ApplicationDomainException;
import com.kruger.krugertest.infraestructure.repositories.UserRepository;

/**
 *
 * @author diego
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String identification) throws UsernameNotFoundException {
        Optional<User> userRes = userRepository.getByIdentification(identification);
        if (!userRes.isPresent()) {
            throw new ApplicationDomainException("Could not find User with identification = " + identification);
        }
        User user = userRes.get();
        return new org.springframework.security.core.userdetails.User(
                identification,
                user.getPassword(),
                getGrantedAuthorities(user.getRoles()));
    }

    private List<GrantedAuthority> getGrantedAuthorities(Collection<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
}
