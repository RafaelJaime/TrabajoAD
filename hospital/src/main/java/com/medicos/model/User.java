package com.medicos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.userdetails.User.UserBuilder;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(name="username",unique=true,nullable=false)
	private String username;
	
	@Column(name="password",nullable=false)
	private String password;
	
	private boolean enabled;
	
	private String role;

	public User(long id, String username, String password, boolean enabled, String role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.role = role;
	}

	public User() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public static UserBuilder withUsername(String username2) {
		// TODO Auto-generated method stub
		return null;
	}
}
