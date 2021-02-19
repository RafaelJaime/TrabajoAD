package com.medicos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.medicos.interfaceService.IMedicAppoService;
import com.medicos.interfaces.IMedicalAppointment;
import com.medicos.model.MedicalAppointment;

@Service
public class MedicAppointmentService implements IMedicAppoService {
	
	@Autowired
	private IMedicalAppointment data;
	
	@Override
	public int save(MedicalAppointment u) {
		int res = 0;
		MedicalAppointment user = data.save(u);
		if (!user.equals(null))
			res = 1;
		return res;
	}

}
