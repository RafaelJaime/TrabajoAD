package com.medicos.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.medicos.model.Medicine;
import com.medicos.model.User;

@Repository
public interface IMedicine extends CrudRepository<Medicine, Integer>{
	public Medicine findByName(String name);
	@Query("SELECT m FROM Medicine m where stock > 0")
	public List<Medicine> findWithStock();
}