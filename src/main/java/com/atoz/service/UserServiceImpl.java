package com.atoz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atoz.dao.UserDao;
import com.atoz.schr.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	UserDao userDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User findById(Integer id) {
		return userDao.findById(id);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public void save(User user) {
		userDao.save(user);
	}

	@Override
	public void delete(int id) {
		userDao.delete(id);
	}

	@Override
	public void Update(User user) {
		userDao.update(user);
	}

}