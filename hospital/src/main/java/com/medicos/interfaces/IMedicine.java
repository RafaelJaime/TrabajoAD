package com.medicos.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medicos.model.Medicine;

@Repository
public interface IMedicine extends JpaRepository<Medicine, Integer>{
	Medicine findByName(String name);
	Medicine findById(int id);
} 