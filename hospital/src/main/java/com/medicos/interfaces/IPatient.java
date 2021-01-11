package com.medicos.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.medicos.model.Patient;

@Repository
public interface IPatient extends CrudRepository<Patient, Integer>{

}
