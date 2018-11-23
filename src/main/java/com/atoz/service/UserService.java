package com.atoz.service;

import java.util.List;

import com.atoz.schr.*;

public interface UserService {

	User findById(Integer id);
	
	List<User> findAll();

	void save(User user);
	
	void Update(User user);
	
	void delete(int id);
	
}