package com.aoide.controller;

import javax.validation.constraints.Size;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Email;

import lombok.Getter;
import lombok.Setter;

import com.aoide.model.member.Account;

@Setter
@Getter
public class UserInputs
{
	@Email( groups = { LoginInput.class, RegisterInput.class }, message = "Email should be valid" )
	private String email;

	// validates that the annotated property value has a size between the attributes min and max
    @Size( min = 10, max = 20,  groups = { LoginInput.class, RegisterInput.class },
		   message = "password size must be between 10 and 20" )
	private String password;

	// validates that the property is not null or whitespace
	@NotBlank( groups = { RegisterInput.class }, message = "name cannot be null or empty string" )
	private String name;

	public UserInputs() {}

	public UserInputs( String email, String password )
	{
		this.email = email;
		this.password = password;
	}

	public Account toAccount()
	{
		Account acc = new Account();
		acc.setEmail( email );
		acc.setPassword( password );
		acc.setName( name );

		return acc;
	}

	interface LoginInput {
        // validation group marker interface
    }

    interface RegisterInput {
        // validation group marker interface
    }
}
