package com.kruger.krugertest.application.queries;

import com.kruger.krugertest.domain.entities.Employee;

import io.jkratz.mediator.core.Request;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReadEmployeeByIdentificationQuery implements Request<Employee> {


}
