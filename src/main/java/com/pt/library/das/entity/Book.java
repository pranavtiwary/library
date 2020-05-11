package com.pt.library.das.entity;

import java.io.Serializable;

public class Book implements Serializable{

	private static final long serialVersionUID = -5232272598770950231L;
	
	private String name;
	private String author;
	private int availabiltyCount;
	
	public Book(String name, String author, int availabiltyCount) {
		super();
		this.name = name;
		this.author = author;
		this.availabiltyCount = availabiltyCount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	public void decrementAvailabilityCount() {
		this.availabiltyCount--;
	}

	public void incrementAvailabilityCount() {
		this.availabiltyCount++;
	}

	public int getAvailabiltyCount() {
		return availabiltyCount;
	}

	public void setAvailabiltyCount(int availabiltyCount) {
		this.availabiltyCount = availabiltyCount;
	}

}
