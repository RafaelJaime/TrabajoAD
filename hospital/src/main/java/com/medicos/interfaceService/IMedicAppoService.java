package com.medicos.interfaceService;

import java.util.Collection;
import java.util.List;

import com.medicos.model.MedicalAppointment;

public interface IMedicAppoService {
	public Collection<MedicalAppointment> findNoDescription();
}
