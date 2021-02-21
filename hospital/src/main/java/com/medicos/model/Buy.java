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
	private float price;
	
	@ManyToOne
	private User Patient_id;
	
	public Buy() {};
	
	public Buy(int id, Date date, int prize, User Patient_id) {
		super();
		this.id = id;
		this.date = date;
		this.price = prize;
		this.Patient_id = Patient_id;
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
		return price;
	}
	public void setPrize(float prize) {
		this.price = prize;
	}
	public User getPatient_id() {
		return Patient_id;
	}
	public void setPatient_id(User idPatient) {
		this.Patient_id = idPatient;
	}
	
	@Override
	public String toString() {
		return "Buy [id=" + id + ", date=" + date + ", prize=" + price + ", idPatient=" + Patient_id + "]";
	}
}