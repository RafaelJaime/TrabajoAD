package com.medicos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicos.interfaceService.IUserService;
import com.medicos.interfaces.IUser;
import com.medicos.model.User;

@Service
public class UserService implements IUserService{

	@Autowired
	private IUser data;

	@Override
	public List<com.medicos.model.User> list() {
		// TODO Auto-generated method stub
		return (List<com.medicos.model.User>) data.findAll();
	}
	@Override
	public List<com.medicos.model.User> listMedics() {
		return (List<com.medicos.model.User>) data.findByRole("MEDIC");
	}
	@Override
	public List<com.medicos.model.User> listPatients() {
		return (List<com.medicos.model.User>) data.findByRole("PATIENT");
	}

	@Override
	public Optional<com.medicos.model.User> listId(int id) {
		return data.findById(id);
	}

	@Override
	public int save(com.medicos.model.User u) {
		int res=0;
		com.medicos.model.User user=data.save(u);
		if(!user.equals(null))
			res=1;
		return res;
	}

	@Override
	public void delete(int id) {
		data.deleteById(id);
	}

	@Override
	public List<User> findByRole(String role) {
		return (List<com.medicos.model.User>) data.findByRole(role);
	}

	@Override
	public com.medicos.model.User findByName(String name) {
		return data.findByName(name);
	}
}
