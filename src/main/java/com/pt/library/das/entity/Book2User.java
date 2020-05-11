package com.pt.library.das.entity;

import java.util.Date;

/**
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class Book2User {

	private String userName;
	private Date lendOn;
	private Date returnedOn;
	
	public Book2User(String userName, Date lendOn) {
		this.userName=userName;
		this.lendOn=lendOn;
	}
	
	
	public Book2User(String userName, Date lendOn, Date returnedOn) {
		super();
		this.userName = userName;
		this.lendOn = lendOn;
		this.returnedOn = returnedOn;
	}


	public Date getLendOn() {
		return lendOn;
	}
	public void setLendOn(Date lendOn) {
		this.lendOn = lendOn;
	}
	public Date getReturnedOn() {
		return returnedOn;
	}
	public void setReturnedOn(Date returnedOn) {
		this.returnedOn = returnedOn;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
