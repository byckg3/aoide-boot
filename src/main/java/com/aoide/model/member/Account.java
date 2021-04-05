package com.aoide.model.member;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;

import org.springframework.security.core.userdetails.UserDetails;

@Data
@Entity
@Table( name = "accounts" )
public class Account implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column( name = "id" )
	private Long id;

	@Column( name = "email", unique = true )
	private String email;

	@Column( name = "password" )
	private String password;

	@Column( name = "name" )
	private String name;
	
	@Column( name = "salt", nullable = true )
	private String salt;

	@Column( name = "role" )
	private String role;

	private boolean enabled = true;
}
