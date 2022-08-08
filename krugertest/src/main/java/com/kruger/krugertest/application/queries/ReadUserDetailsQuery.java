package com.kruger.krugertest.application.queries;

import com.kruger.krugertest.domain.entities.User;

import io.jkratz.mediator.core.Request;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReadUserDetailsQuery implements Request<User> {


}
