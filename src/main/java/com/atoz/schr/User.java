package com.atoz.schr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userinfo")
public class User{
	
	/**
	 * 
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Integer ide;

	@Column(name="name")
	private String name;

	@Column(name="address")
	private String Address;
	
	@Column(name="email")
	private String email;

	public Integer getId() {
		return ide;
	}

	public void setId(Integer id) {
		this.ide = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString(){
		return "User [ide="+ ide +",name="+name+",Address="+Address+",email="+email+"]";
		
	}
}
