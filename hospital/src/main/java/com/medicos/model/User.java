package com.medicos.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

@Entity
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length=50,unique=true)
    private String name;

    @Column(length=60)
    private String password;

    @Column(length=30)
    private String firstname;

    @Column(length=30)
    private String role;

    //Medicos
    @Column(length=50)
    private String surname;

    private int age;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date date;

    @Column(length=40)
    private String specialty;

    @Column(length=100)
    private String direction;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getSpecialty() {
        return specialty;
    }
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
	
	public User() {
		super();
	}
	public User(int id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		setRole("ROLE_USER");
	}
	
	public String fullName() {
		return this.firstname + " " + this.surname;
	}

}
