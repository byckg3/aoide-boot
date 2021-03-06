package com.aoide.user.model;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Email;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AccountDTO
{
	@Email( groups = { LoginInput.class, RegisterInput.class }, message = "invalid email" )
	private String email;

	// validates that the annotated property value has a size between the attributes min and max
    @Size( min = 10, max = 20,  groups = { LoginInput.class, RegisterInput.class },
		   message = "password size must be between 10 and 20" )
	private String password;

	// validates that the property is not null or whitespace
	@NotBlank( groups = { RegisterInput.class }, message = "name cannot be null or empty string" )
	private String name;

	public AccountDTO() {}

	public AccountDTO( String email, String password )
	{
		this.email = email;
		this.password = password;
	}

	public Account toEntity()
	{
		Account acc = new Account();
		acc.setEmail( email );
		acc.setPassword( password );
		acc.setName( name );

		return acc;
	}

	public interface LoginInput {
        // validation group marker interface
    }

    public interface RegisterInput {
        // validation group marker interface
    }
}
