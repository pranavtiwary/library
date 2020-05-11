package com.pt.library.das.repo;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.pt.library.das.entity.User;

/**
 * User repository
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class UserRepository implements IUserRepository {

	private static final HashMap<String, User> userData = new HashMap<>();

	public User saveUser(User user){
		userData.put(user.getName(), user);
		return user;
	}

	@Override
	public void deleteuser(String userName) {
		userData.remove(userName);
	}

	@Override
	public User findUserByName(String  userName) {
		return userData.get(userName);
	}

	@Override
	public List<User> findAll() {
		List<User> users = userData.values().stream()
				.collect(Collectors.toList());
		return users;
	}

}
