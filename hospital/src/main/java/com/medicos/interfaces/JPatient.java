package com.medicos.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.medicos.model.Patient;

public interface JPatient extends JpaRepository<Patient, Long> {
	@Query("SELECT u FROM Patient u WHERE u.username = ?1")
    public Patient findByUsername(String username);
}