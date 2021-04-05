package com.aoide.controller;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Email;

public class LoginForm
{
    // @NotBlank( message = "email cannot be null or empty string" ) 				// validates that the property is not null or whitespace
    // @Size( min = 8, max = 20, message = "email size must be between 8 and 20" )	// validates that the annotated property value has a size between the attributes min and max
	@Email( message = "Email should be valid" )
	private String email;

    @Size(min = 10, max = 20, message = "password size must be between 10 and 20" )
	private String password;

	public LoginForm() {}

	public LoginForm( String email, String password )
	{
		this.email = email;
		this.password = password;
	}

    public String getEmail() {
		return email;
	}
	public void setEmail( String email ) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword( String password ) {
		this.password = password;
	}
}
