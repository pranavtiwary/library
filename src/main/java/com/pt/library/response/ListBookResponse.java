package com.pt.library.response;

import java.util.ArrayList;
import java.util.List;

import com.pt.library.dto.BookDetails;
/**
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class ListBookResponse extends Response {
	private List<BookDetails> books= new ArrayList<>();
	public ListBookResponse(boolean isSuccess, String message) {
		super(isSuccess, message);
	}
	public List<BookDetails> getBooks() {
		return books;
	}
}
