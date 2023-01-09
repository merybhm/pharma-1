package com.pharma.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
public class Pharmacie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	private String adresse;
	private double latitude;
	private double longitude;
	@ManyToOne
	private Ville ville ;
	@ManyToOne
	private Zone zone ;
	@OneToMany
	private List<DatePharm> datesPharm;

	
	public Pharmacie() {
		super();
	}
	public Pharmacie(String nom, String adresse, Double latitude, double longitude, Zone zone ) {
		super();
		this.nom = nom;
		this.adresse = adresse;
		this.latitude = latitude;
		this.longitude = longitude;
		this.zone= zone ;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public Zone getZone() {
		return zone;
	}
	public void setZone(Zone zone) {
		this.zone = zone;
	}
	public Ville getVille() {
		return ville;
	}
	public void setVille(Ville ville) {
		this.ville = ville;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public Pharmacie(int id, String nom, String adresse, double latitude, double longitude, Ville ville, Zone zone) {
		super();
		this.id = id;
		this.nom = nom;
		this.adresse = adresse;
		this.latitude = latitude;
		this.longitude = longitude;
		this.ville = ville;
		this.zone = zone;
	}
	
	
	
}
