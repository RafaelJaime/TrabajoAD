package com.medicos.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medicos.model.User;

@Repository
public interface IUser extends JpaRepository<User, Integer>{
    List<User> findByRole(String role);
	User findByName(String name);
}
