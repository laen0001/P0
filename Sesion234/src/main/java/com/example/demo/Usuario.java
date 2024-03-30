package com.example.demo;
import java.io.Serializable;
public class Usuario implements Serializable {
	private Integer id;
	private String user;
	private String password;
	
	public Usuario(String user,String password ) {
		super();
		
		this.id = id;
	
		this.user = user;
		this.password = password;
		
		
	}
	public Usuario() {
		
		
		user ="";
		password = "";
		
		
		
	}
	
	
	

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Usuario [user=" + user + ", password=" + password + "]";
	}
	
	
	

	

}
