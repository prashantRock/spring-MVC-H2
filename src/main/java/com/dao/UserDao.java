package com.dao;

import java.util.List;

import com.model.User;

public interface UserDao {

	User findByName(String name);
	
	List<User> findAll();

}