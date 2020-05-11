package com.pt.library.ui;

import com.pt.library.das.entity.SubscriptionType;
import com.pt.library.das.entity.User;
import com.pt.library.response.ListUserResponse;
import com.pt.library.response.SearchUserReponse;
import com.pt.library.service.IUserService;
import com.pt.library.service.impl.UserServiceImpl;
import com.pt.library.utility.IOUtility;

public class UserFlow {

	public static final String USER_CREATE_MSG="Welcome to Create User\n";
	public static final String USER_CREATE_NAME_MSG="Please enter your name\n";
	public static final String USER_CREATE_ADDRESS_MSG="Please enter your address\n";
	public static final String USER_CREATE_SUBSCRIPTION_TYPE_MSG="Please enter subscription type 1 for monthly and 2 for yearly\n";
	public static final String USER_DISPLAY_MSG="Displaying User\n";
	public static final String USER_STATUS_MSG="Enter name to check User Status\n";
	
	
	private IUserService userService;

	public UserFlow(){
		userService=new UserServiceImpl();
	}

	public void startCreateUserFlow(){
		IOUtility.writeOnConsole(USER_CREATE_MSG);
		String name=IOUtility.readLineUntil(USER_CREATE_NAME_MSG);
		String address=IOUtility.readLineUntil(USER_CREATE_ADDRESS_MSG);
		int subscriptionType=IOUtility.readIntegerUntil(1, 2, USER_CREATE_SUBSCRIPTION_TYPE_MSG);
		SubscriptionType type=null;
		switch(subscriptionType){
		case 1:
			type=SubscriptionType.MONTHLY;
			break;
		case 2:
			type=SubscriptionType.YEARLY;
			break;
		}
		userService.saveUser(new User(name, address, type));
	}
	
	public void displayUser(){
		IOUtility.writeOnConsole(USER_DISPLAY_MSG);
		ListUserResponse listAllUser = userService.listAllUser();
		listAllUser.getUsers().stream().forEach(System.out::println);
	}

	public void displayUserStatus() {
		IOUtility.writeOnConsole(USER_STATUS_MSG);
		String name=IOUtility.readLineUntil(USER_CREATE_NAME_MSG);
		SearchUserReponse user = userService.findUserByName(name);
		if(user.isSuccess()){
			IOUtility.writeOnConsole(user.getUserDetails().toString());
		}else{
			IOUtility.writeOnConsole(user.getMessage());
		}
	}

}
