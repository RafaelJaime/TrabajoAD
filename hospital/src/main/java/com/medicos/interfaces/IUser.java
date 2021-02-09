package com.medicos.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medicos.model.User;

public interface IUser extends JpaRepository<User, Integer>{

}
