package com.medicos.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medicos.model.Buy;
import com.medicos.model.User;

@Repository
public interface IBuy extends JpaRepository<Buy, Integer>{
		
	List<Buy> findById(User patient);
	
}
