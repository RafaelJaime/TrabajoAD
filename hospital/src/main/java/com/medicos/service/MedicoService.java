package com.medicos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicos.interfaceService.IMedicoService;
import com.medicos.interfaces.IMedico;
import com.medicos.model.Medico;

@Service
public class MedicoService implements IMedicoService{
	
	@Autowired
	private IMedico data;
	
	@Override
	public List<Medico> list() {
		// TODO Auto-generated method stub
		return (List<Medico>) data.findAll();
	}

	@Override
	public Optional<Medico> listId(int id) {
		return data.findById(id);
	}

	@Override
	public int save(Medico m) {
		int res=0;
		Medico medico=data.save(m);
		if(!medico.equals(null))
			res=1;
		return res;
	}

	@Override
	public void delete(int id) {
		data.deleteById(id);
	}

}
