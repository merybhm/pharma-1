package com.example.pharmacieapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Garde {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idGarde;
	
	private String type;
	
	@OneToMany(mappedBy = "garde",fetch = FetchType.EAGER)
	@JsonIgnore
	private List<PharmacieDeGarde> gardes ;
	
	public Garde(int idGarde, String type) {
		this.idGarde = idGarde;
		this.type = type;
	}

	public void addPharmacieDeGarde(PharmacieDeGarde garde){
		this.gardes.add(garde);
		garde.setGarde(this);
	}

	public Garde(String type) {
		this.type = type;
	}

	public Garde() {
		super();
		this.gardes=new ArrayList<>();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getIdGarde() {
		return idGarde;
	}

	public void setIdGarde(int idGarde) {
		this.idGarde = idGarde;
	}

	public List<PharmacieDeGarde> getGardes() {
		return gardes;
	}

	public void setGardes(List<PharmacieDeGarde> gardes) {
		this.gardes = gardes;
	}
	
	
}
