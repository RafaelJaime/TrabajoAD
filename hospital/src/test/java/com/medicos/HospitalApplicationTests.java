package com.medicos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.medicos.interfaces.IUser;
import com.medicos.model.User;

@SpringBootTest
class HospitalApplicationTests {
	
	@Autowired
	private IUser userRepo;
	
	@Test
	void register() {
		User user=new User(1,"A","A",true,"ROLE_CLIENT");
		
		User retorno = userRepo.save(user);

	}

}
