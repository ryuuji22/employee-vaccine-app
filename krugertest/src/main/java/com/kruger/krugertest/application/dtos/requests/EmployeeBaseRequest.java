package com.kruger.krugertest.application.dtos.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeBaseRequest {
	
	@NotBlank(message = "Identification field can't be empty.")
	private String identification;

	@Pattern(regexp = "^[a-zA-Z]*$", message = "First Name must have only alphabetic characters")
	@NotBlank(message = "First Name field can't be empty.")
	private String firstName;

	@Pattern(regexp = "^[a-zA-Z]*$", message = "Last Name must have only alphabetic characters")
	@NotBlank(message = "Last Name field can't be empty.")
	private String lastName;

	@Pattern(regexp = "^[a-zA-Z0-9.\\-\\/+=@_ ]*$")
	@NotBlank(message = "Email field can't be empty.")
	@Email(message = "You must provide a valid email format")
	private String email;

}
