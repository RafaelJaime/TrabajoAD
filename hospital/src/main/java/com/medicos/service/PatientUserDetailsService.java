package com.medicos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.medicos.interfaces.JPatient;
import com.medicos.model.Patient;

public class PatientUserDetailsService implements UserDetailsService {
	@Autowired
    private JPatient jpatient;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Patient patient = jpatient.findByUsername(username);
        if (patient == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new PatientUserDetails(patient);
	}
}
