package com.pt.library.service;

import com.pt.library.das.entity.User;
import com.pt.library.response.CreateUserResponse;
import com.pt.library.response.ListUserResponse;
import com.pt.library.response.Response;
import com.pt.library.response.SearchUserReponse;

public interface IUserService {

	boolean checkIfNameTaken(String name);
	CreateUserResponse saveUser(User user);
	Response deleteUser(String userName);
	SearchUserReponse findUserByName(String userName);
	ListUserResponse listAllUser();
}
