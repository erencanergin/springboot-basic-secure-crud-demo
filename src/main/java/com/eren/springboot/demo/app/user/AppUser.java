package com.eren.springboot.demo.app.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.eren.springboot.demo.app.validation.FieldMatch;

import lombok.Data;

@Data
@FieldMatch.List({@FieldMatch(first = "password", second = "matchingPassword", message = "Passwords must match.")})
public class AppUser {
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String userName;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String password;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String matchingPassword;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String firstName;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String lastName;

	//@Pattern(regexp = "^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$", message = "Invalid email")
	@NotNull(message = "is required")
	@Pattern(message = "Invalid email", regexp = "^^(.+)@(.+)$")
	@Size(min = 1, message = "is required")
	private String email;
}
