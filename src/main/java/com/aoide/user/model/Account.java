package com.aoide.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.aoide.base.model.BaseEntity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@Entity
@EntityListeners( value = AuditingEntityListener.class )
@Table( name = "accounts" )
public class Account extends BaseEntity implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;

	@Column( name = "email", unique = true )
	private String email;

	private String password;

	private String name;

	private boolean enabled;

	@Enumerated( EnumType.STRING )
	private Role role;
}
