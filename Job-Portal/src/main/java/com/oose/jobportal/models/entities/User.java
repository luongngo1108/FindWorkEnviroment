package com.oose.jobportal.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table (name = "user")
public class User {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "userID", nullable = false)
	private int userID;
	
	@Column(name = "gmail", nullable = false , length = 30)
	private String gmail;
	
	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "role", nullable = false)
	private int role;
	
	@Column(name = "name", nullable = true, length = 50)
	private String name;
	
	@Column(name = "location", nullable = true, length = 150)
	private String location;
	
	@Column(name = "phonenumber", nullable = true, length = 15)
	private String phonenumber;

	@Column(name = "accountnumber", nullable = true, length = 20)
	private String accountnumber;

	@Column
	private String imageUser;
}
