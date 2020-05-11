package com.pt.library.response;

import com.pt.library.dto.BookDetails;
/**
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class CreateBookResponse extends Response {
	private BookDetails bookDetails;
	
	public CreateBookResponse(boolean isSuccess, String message) {
		super(isSuccess, message);
	}

	public BookDetails getBookDetails() {
		return bookDetails;
	}

	public void setBookDetails(BookDetails bookDetails) {
		this.bookDetails = bookDetails;
	}
}
