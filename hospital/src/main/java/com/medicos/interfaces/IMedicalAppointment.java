package com.medicos.interfaces;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.medicos.model.MedicalAppointment;
import com.medicos.model.User;

public interface IMedicalAppointment extends JpaRepository<MedicalAppointment, Integer> {
	Collection<MedicalAppointment> findByObservations(String observations);
	@Query("SELECT u FROM MedicalAppointment u WHERE date = ?1")
	List<MedicalAppointment> findByObservationsNotNull(Date date);
	@Query("SELECT u FROM MedicalAppointment u WHERE patient_id = ?1 AND observations IS NOT NULL")
	List<MedicalAppointment> findByPatient_id(Integer id);
	List<MedicalAppointment> findByDate(Date date);
}
