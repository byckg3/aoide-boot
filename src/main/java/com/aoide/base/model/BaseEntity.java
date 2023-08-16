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

import lombok.Data;

@Data
@EntityListeners( value = AuditingEntityListener.class )
@MappedSuperclass  // A mapped superclass has no separate table defined for it.
public abstract class BaseEntity  // base class for entity with common properties
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
