package com.jc.party.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "tb_person")
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "person_id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "age")
	private Integer age;
	
	
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
	private Set<Hability> habilities = new HashSet<>();
	
	@ManyToMany
	@JsonBackReference
	@JoinTable(name = "tb_person_party", 
			joinColumns = @JoinColumn(name = "person_id", referencedColumnName = "person_id"), 
			inverseJoinColumns = @JoinColumn(name = "party_id", referencedColumnName = "party_id")
	)
	private Set<Party> parties = new HashSet<>();
	
	
	public Person() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Set<Hability> getHabilities() {
		return habilities;
	}

	public void setHabilities(Set<Hability> habilities) {
		this.habilities = habilities;
	}

	public Set<Party> getParties() {
		return parties;
	}

	public void setParties(Set<Party> parties) {
		this.parties = parties;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + ", habilities=" + habilities + ", parties="
				+ parties + "]";
	}
}