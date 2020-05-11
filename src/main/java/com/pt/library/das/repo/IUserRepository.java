package com.pt.library.das.repo;

import java.util.List;

import com.pt.library.das.entity.User;

public interface IUserRepository {
	public User saveUser(User user);
	public User findUserByName(String name);
	public void deleteuser(String userName);
	public List<User> findAll();
}
