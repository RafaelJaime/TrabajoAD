package com.medicos.interfaces;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.medicos.model.MedicalAppointment;

public interface IMedicalAppointment extends JpaRepository<MedicalAppointment, Integer> {
	Collection<MedicalAppointment> findByObservations(String observations);
}
