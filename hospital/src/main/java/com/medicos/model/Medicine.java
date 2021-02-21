package com.medicos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="medicine")
public class Medicine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length=50)
	private String name;
	
	@Column(length=100)
	private String description;
	
	@Column(columnDefinition="char(1)")
	private char prescription;
	
	@Column
	private float price;
	
	@Column
	private int stock;

	public Medicine(int id, String name, String description, char prescription, float price, int stock) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.prescription = prescription;
		this.price = price;
		this.stock = stock;
	}

	public Medicine() {}

	public boolean sinExistencias() {
		return this.stock<= 0;
	}
	public void aumentarStock(int numero) {
		this.stock += numero;
	}
	public void reducirStock(int numero) {
		this.stock-= numero;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public char getPrescription() {
		return prescription;
	}

	public void setPrescription(char prescription) {
		this.prescription = prescription;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
}
