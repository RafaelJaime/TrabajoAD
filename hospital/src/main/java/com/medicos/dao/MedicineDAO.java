package com.medicos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medicos.model.Medicine;

public interface MedicineDAO extends JpaRepository<Medicine, Integer>{

}
