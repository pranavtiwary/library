package com.pt.library.response;

import java.util.ArrayList;
import java.util.List;

import com.pt.library.dto.UserDetails;

/**
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class ListUserResponse extends Response {

	private List<UserDetails> users= new ArrayList<>();
	
	public ListUserResponse(boolean isSuccess, String message) {
		super(isSuccess, message);
		// TODO Auto-generated constructor stub
	}

	public List<UserDetails> getUsers() {
		return users;
	}
}
