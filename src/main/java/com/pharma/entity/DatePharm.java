package com.pharma.entity;

import java.sql.Date;



import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity

public class DatePharm {
	
	
	@EmbeddedId
	private DatePharmPk pk = new DatePharmPk();
	private Date dateFin;
	@ManyToOne
	@JoinColumn(name = "pharmacie",insertable = false, updatable = false)
	@MapsId("pharmacie")
	private Pharmacie pharmacie;
	@ManyToOne
	@JoinColumn(name = "garde",insertable = false, updatable = false)
	@MapsId("garde")
	private Garde garde;
	
	
	public Date getDateFin() {
		return dateFin;
	}
	
	
	public void setDateFin(java.util.Date dateFin) {
		this.dateFin= (Date) dateFin;
	}


	public DatePharm() {
		super();
	}
	public DatePharm(Date dateFin) {
		super();
		this.dateFin = dateFin;
	}
	public DatePharmPk getPk() {
		return pk;
	}
	public void setPk(DatePharmPk pk) {
		this.pk = pk;
	}
	public Pharmacie getPharmacie() {
		return pharmacie;
	}
	public void setPharmacie(Pharmacie pharmacie) {
		this.pharmacie = pharmacie;
	}
	public Garde getGarde() {
		return garde;
	}
	public void setGarde(Garde garde) {
		this.garde = garde;
	}
	public DatePharm(Date dateFin, Pharmacie pharmacie, Garde garde) {
		super();
		this.dateFin = dateFin;
		this.pharmacie = pharmacie;
		this.garde = garde;
}
	
		
	
}
