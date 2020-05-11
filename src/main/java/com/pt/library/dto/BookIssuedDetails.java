package com.pt.library.dto;

import java.util.Date;

/**
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class BookIssuedDetails {

	private String userName;
	private String bookName;
	private Date lendOn;
	private Date returnedOn;

	public BookIssuedDetails(String userName, String bookName, Date lendOn, Date returnedOn) {
		this.userName = userName;
		this.setBookName(bookName);
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

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	@Override
	public String toString() {
		return "BookIssuedDetails [userName=" + userName + ", bookName=" + bookName + ", lendOn=" + lendOn
				+ ", returnedOn=" + returnedOn + "]";
	}
}
