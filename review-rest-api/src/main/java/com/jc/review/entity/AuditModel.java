package com.jc.review.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Indica que es una superclase y no una clase entidad
@MappedSuperclass 
//Indica la clase que actualiza la información en cada registro
@EntityListeners(AuditingEntityListener.class)
//Se coloca para que no se serialize y se omita
@JsonIgnoreProperties(
		value = {"creationDate", "updateDate"},
		allowGetters = true
)
public abstract class AuditModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_date", nullable = false, updatable = false)
	@CreatedDate
	private Date creationDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date", nullable = false)
	@LastModifiedDate
	private Date updateDate;
	

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}