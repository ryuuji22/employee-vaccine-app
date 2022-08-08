package com.kruger.krugertest.infraestructure.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

	private String message;
	private String error;

	public ErrorResponse(Exception e, String error) {
		this.message = e.getMessage();
		this.error = error;

	}

}
