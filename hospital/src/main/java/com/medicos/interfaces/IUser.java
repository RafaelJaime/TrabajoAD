package com.medicos.interfaces;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medicos.model.User;

@Repository("userRepository")
public interface IUser extends JpaRepository<User, Serializable>{
	public abstract User findByUsername(String username);
	
}
