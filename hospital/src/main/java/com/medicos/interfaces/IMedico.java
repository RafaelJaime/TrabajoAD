package com.medicos.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.medicos.model.Medico;

@Repository
public interface IMedico extends CrudRepository<Medico, Integer>{

}
