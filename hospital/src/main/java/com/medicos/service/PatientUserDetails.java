package com.medicos.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.medicos.model.Patient;

public class PatientUserDetails implements UserDetails {
	private Patient patient;
	
	public PatientUserDetails(Patient patient) {
		this.patient = patient;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		return patient.getPassword();
	}

	@Override
	public String getUsername() {
		return patient.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	public String getFullName() {
		return patient.getName() + " " + patient.getSurname();
	}

}
