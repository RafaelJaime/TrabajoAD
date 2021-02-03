package com.medicos.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BuyMedicine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private Medicine idMedicine;
	
	@ManyToOne
	private Buy idBuy;
	
	public BuyMedicine() {}
	
	public BuyMedicine(int id, Medicine idMedicine, Buy idBuy) {
		super();
		this.id = id;
		this.idMedicine = idMedicine;
		this.idBuy = idBuy;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Medicine getIdMedicine() {
		return idMedicine;
	}
	public void setIdMedicine(Medicine idMedicine) {
		this.idMedicine = idMedicine;
	}
	public Buy getIdBuy() {
		return idBuy;
	}
	public void setIdBuy(Buy idBuy) {
		this.idBuy = idBuy;
	}
	
	@Override
	public String toString() {
		return "BuyMedicine [id=" + id + ", idMedicine=" + idMedicine + ", idBuy=" + idBuy + "]";
	}
	
}
