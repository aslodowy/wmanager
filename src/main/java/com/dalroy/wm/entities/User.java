package com.dalroy.wm.entities;

import javax.persistence.*;

import com.dalroy.wm.utilities.Password;

@Entity
@Table(name="users", schema="WMDatabase")
public class User {
	public User() {}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="role")
	private String role;
	
	@Column(name="salt")
	private String salt;
	
	public int getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getRole() {
		return role;
	}
	
	public String getSalt() {
		return salt;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public void setSalt(String salt) {
		this.salt = salt;
	}
}
