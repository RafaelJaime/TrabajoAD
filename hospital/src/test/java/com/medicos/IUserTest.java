package com.medicos;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.medicos.interfaces.JPatient;
import com.medicos.model.Patient;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class IUserTest {
	@Autowired
    private TestEntityManager entityManager;
     
    @Autowired
    private JPatient repo;
    @Test
    public void testCreateUser() {
        Patient user = new Patient();
        user.setUsername("ravikumar@gmail.com");
        user.setPassword("ravi2020");
        user.setName("Ravi");
        user.setSurname("Kumar");
        
        Patient savedUser = repo.save(user);
         
        Patient existUser = entityManager.find(Patient.class, savedUser.getId());
         
        assertThat(user.getUsername()).isEqualTo(existUser.getUsername());
         
    }
}
