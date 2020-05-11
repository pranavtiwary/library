package com.pt.library.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class UserDetails{

	private String userName;
	private String address;
	private List<BookIssuedDetails> issueDetails= new ArrayList<>();
	
	public UserDetails(String userName, String address) {
		this.userName = userName;
		this.address = address;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<BookIssuedDetails> getIssueDetails() {
		return issueDetails;
	}
	@Override
	public String toString() {
		return "UserDetails [userName=" + userName + ", address=" + address + ", issueDetails=" + issueDetails + "]";
	}
}
