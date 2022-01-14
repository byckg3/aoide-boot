package com.aoide.base.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EntityListeners( value = AuditingEntityListener.class )
@MappedSuperclass  // base class for entity with common properties
public abstract class BaseEntity
{
    @Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private Long id;

    @CreatedDate
	@Column( name = "created_date", nullable = false, updatable = false )
	private Date createdDate;

	@LastModifiedDate
	@Column( name = "last_modified" )
	private Date lastModified;

	@Version
	@Column
	private Integer version;
}
