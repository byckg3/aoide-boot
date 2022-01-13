package com.aoide.user.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@Entity
@EntityListeners( value = AuditingEntityListener.class )
@Table( name = "accounts" )
public class Account implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;

	@Column( name = "email", unique = true )
	private String email;

	private String password;

	private String name;

	private boolean enabled;

	@Enumerated( EnumType.STRING )
	private Role role;
	
	@CreatedDate
	@Column( name = "created_date", nullable = false, updatable = false )
	private Date createdDate;

	@LastModifiedDate
	@Column( name = "modified_date" )
	private Date modifiedDate;
}
