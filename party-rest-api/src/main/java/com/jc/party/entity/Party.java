package com.jc.party.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_party")
public class Party {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "party_id")
	private Long id;
	
	@Column(name = "location")
	private String location;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "party_date")
	private Date partyDate;
	

	@ManyToMany
	@JoinTable(name = "tb_person_party", 
			joinColumns = @JoinColumn(name = "party_id", referencedColumnName = "party_id"), 
			inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "person_id")
	)
	private Set<Person> people = new HashSet<>();


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getPartyDate() {
		return partyDate;
	}

	public void setPartyDate(Date partyDate) {
		this.partyDate = partyDate;
	}

	public Set<Person> getPeople() {
		return people;
	}

	public void setPeople(Set<Person> people) {
		this.people = people;
	}

	@Override
	public String toString() {
		return "Party [id=" + id + ", location=" + location + ", partyDate=" + partyDate + ", people=" + people + "]";
	}	
}