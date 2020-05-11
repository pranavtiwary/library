package com.pt.library.das.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User class hold user info
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class User implements Serializable{
	
	private static final long serialVersionUID = 3522947114559171702L;
	
	private String name;
	private String address;
	private SubscriptionType subscriptionType;
	private List<String> issuedBooks=new ArrayList<>();
	
	public User(String name, String address, SubscriptionType subscriptionType) {
		this.name=name;
		this.address=address;
		this.subscriptionType=subscriptionType;
	}
	public SubscriptionType getSubscriptionType() {
		return subscriptionType;
	}
	public void setSubscriptionType(SubscriptionType subscriptionType) {
		this.subscriptionType = subscriptionType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<String> getIssuedBooks() {
		return issuedBooks;
	}
}
