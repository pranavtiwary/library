package com.pt.library.response;

import com.pt.library.dto.UserDetails;

/**
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class SearchUserReponse extends Response {

	private UserDetails userDetails;
	
	public SearchUserReponse(boolean isSuccess, String message) {
		super(isSuccess, message);
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

}
