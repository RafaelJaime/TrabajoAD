package com.medicos.interfaceService;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.medicos.model.User;

public interface IUserService {
	 public List<User>list();
	 public List<User>listMedics();
	 public List<User>listPatients();
	 public Optional<User>listId(int id);
	 public int save(User u);
	 public void delete(int id);
	 List<User> findByRole(String role);
	 User findByName(String name);
}
