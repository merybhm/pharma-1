package com.pharma.entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Garde {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)

	private int id;
	@Enumerated
	private TypeDeGarde type;
	
	
	@OneToMany
	private List<DatePharm> datesPharm;
	
	public Garde() {
		super();
	}
	public Garde(int id, TypeDeGarde type) {
		super();
		this.id = id;
		this.type = type;
	}
	
	public Garde(TypeDeGarde type) {
		super();
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public TypeDeGarde getType() {
		return type;
	}
	public void setType(TypeDeGarde type) {
		this.type = type;
	}
	
		
	}
	
	

