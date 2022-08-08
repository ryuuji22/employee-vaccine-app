package com.kruger.krugertest.application.queries;

import java.util.List;

import com.kruger.krugertest.domain.entities.Employee;

import io.jkratz.mediator.core.Request;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReadEmployeesQuery implements Request<List<Employee>> {


}
