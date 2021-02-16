package com.medicos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.medicos.interfaces.IUser;
import com.medicos.model.User;

@SpringBootTest
class HospitalApplicationTests {

	@Autowired
	private IUser repository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Test
	void CreateUser() {
		User us=new User();
		us.setName("Akira");
		us.setPassword(encoder.encode("akira"));
		User newUser = repository.save(us);
	}

}
