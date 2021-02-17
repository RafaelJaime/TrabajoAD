package com.medicos.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MedicalAppointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private User patient;
	
	@ManyToOne
	private User medic;
	
	@Column
	private Date date;
	
	@Column(length=200)
	private String observations;
	
	public MedicalAppointment() {}
	
	public MedicalAppointment(int id, User patient, User medic, Date date, String observations) {
		super();
		this.id = id;
		this.patient = patient;
		this.medic = medic;
		this.date = date;
		this.observations = observations;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getPatient() {
		return patient;
	}
	public void setPatient(User patient) {
		this.patient = patient;
	}
	public User getMedic() {
		return medic;
	}
	public void setMedic(User medic) {
		this.medic = medic;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
	
	@Override
	public String toString() {
		return "MedicalAppointment [id=" + id + ", patient=" + patient + ", medic=" + medic + ", date=" + date
				+ ", observations=" + observations + "]";
	}
}
