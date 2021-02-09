package com.medicos.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Buy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private Date date;
	
	@Column
	private float prize;
	
	@ManyToOne
	private Patient idPatient;
	
	public Buy() {};
	
	public Buy(int id, Date date, int prize, Patient idPatient) {
		super();
		this.id = id;
		this.date = date;
		this.prize = prize;
		this.idPatient = idPatient;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public float getPrize() {
		return prize;
	}
	public void setPrize(float prize) {
		this.prize = prize;
	}
	public Patient getIdPatient() {
		return idPatient;
	}
	public void setIdPatient(Patient idPatient) {
		this.idPatient = idPatient;
	}
	
	@Override
	public String toString() {
		return "Buy [id=" + id + ", date=" + date + ", prize=" + prize + ", idPatient=" + idPatient + "]";
	}
}