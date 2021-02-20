package com.medicos.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.medicos.model.Medicine;
import com.medicos.model.User;

@Repository
public interface IMedicine extends CrudRepository<Medicine, Integer>{
	Medicine findByName(String name);
}