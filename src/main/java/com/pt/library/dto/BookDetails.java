package com.pt.library.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class BookDetails {

	private String bookName;
	private int availabilityCount;
	private List<BookIssuedDetails> issueDetails=new ArrayList<>();
	
	public BookDetails(String bookName, int availabilityCount) {
		this.bookName = bookName;
		this.availabilityCount = availabilityCount;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public int getAvailabilityCount() {
		return availabilityCount;
	}
	public void setAvailabilityCount(int availabilityCount) {
		this.availabilityCount = availabilityCount;
	}
	public List<BookIssuedDetails> getIssueDetails() {
		return issueDetails;
	}
	@Override
	public String toString() {
		return "BookDetails [bookName=" + bookName + ", availabilityCount=" + availabilityCount + ", issueDetails="
				+ issueDetails + "]";
	}
}
