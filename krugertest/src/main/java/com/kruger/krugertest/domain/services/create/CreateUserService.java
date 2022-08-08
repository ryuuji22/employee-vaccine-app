package com.kruger.krugertest.domain.services.create;

import java.util.List;

import com.kruger.krugertest.domain.entities.User;

import io.jkratz.mediator.core.Request;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserService implements Request<User> {

    private String identification;
    private List<String> roles;

}
