package com.medicos.interfaceService;

import java.util.List;

import com.medicos.model.MedicalAppointment;
import com.medicos.model.User;

public interface IMedicAppoService {
	public int save(MedicalAppointment u);
	public List<MedicalAppointment> list(User patient);
}
