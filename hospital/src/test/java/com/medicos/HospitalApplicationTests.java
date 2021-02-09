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
		us.setId(1);
		us.setName("Ale");
		us.setPassword(encoder.encode("123"));
		User newUser = repository.save(us);
	}

}
