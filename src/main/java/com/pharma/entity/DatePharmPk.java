package com.pharma.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Embeddable;
@Embeddable
public class DatePharmPk implements Serializable{
	private int pharmacie;
	private int garde;
	private Date datedebut;

	public DatePharmPk() {
		super();
	}
	public int getPharmacie() {
		return pharmacie;
	}
	public void setPharmacie(int pharmacie) {
		this.pharmacie = pharmacie;
	}
	public int getGarde() {
		return garde;
	}
	public void setGarde(int garde) {
		this.garde = garde;
	}
	public Date getDatedebut() {
		return datedebut;
	}
	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}
	public DatePharmPk(int pharmacie, int garde, Date datedebut) {
		super();
		this.pharmacie = pharmacie;
		this.garde = garde;
		this.datedebut = datedebut;
	}
	
	
}
