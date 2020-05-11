package com.pt.library.das.entity;

public enum SubscriptionType {

	MONTHLY(5), YEARLY(10);

	private int noOfBookAllowed;

	private SubscriptionType(int noOfBookAllowed){
		this.noOfBookAllowed=noOfBookAllowed;
	}

	public int getNoOfBookAllowed() {
		return noOfBookAllowed;
	}
}
