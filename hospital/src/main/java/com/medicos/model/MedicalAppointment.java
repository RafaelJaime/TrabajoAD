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
	private Patient patient;
	
	@ManyToOne
	private Medico medic;
	
	@Column
	private Date date;
	
	@Column(length=200)
	private String observations;
	
	public MedicalAppointment() {}
	
	public MedicalAppointment(int id, Patient patient, Medico medic, Date date, String observations) {
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
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Medico getMedic() {
		return medic;
	}
	public void setMedic(Medico medic) {
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
