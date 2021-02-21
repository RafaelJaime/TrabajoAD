package com.medicos.interfaceService;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.medicos.model.MedicalAppointment;
import com.medicos.model.User;

public interface IMedicAppoService {
	public int save(MedicalAppointment u);
	@Query("SELECT m FROM MedicalAppointment m WHERE observations IS NULL OR observations IS NOT NULL AND medic_id IS NULL AND medic_id IS NOT NULL")
	public List<MedicalAppointment> list(User patient);
}
