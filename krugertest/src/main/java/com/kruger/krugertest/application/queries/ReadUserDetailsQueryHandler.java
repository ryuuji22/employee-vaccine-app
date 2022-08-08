package com.kruger.krugertest.application.queries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.kruger.krugertest.domain.entities.User;
import com.kruger.krugertest.domain.exceptions.ApplicationDomainException;
import com.kruger.krugertest.domain.interfaces.repositories.IUserRepository;

import io.jkratz.mediator.core.RequestHandler;

@Component
public class ReadUserDetailsQueryHandler implements RequestHandler<ReadUserDetailsQuery, User> {

	@Autowired
    private IUserRepository userRepository;

    @Override
    public User handle(ReadUserDetailsQuery query) {
    	 String identification = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         return userRepository.getByIdentification(identification)
        		 .orElseThrow(() -> new ApplicationDomainException(
        					"User with identification " + identification + " doesn't exist."));
    }

}
