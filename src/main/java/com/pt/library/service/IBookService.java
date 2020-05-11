package com.pt.library.service;

import com.pt.library.response.CreateBookResponse;
import com.pt.library.response.ListBookResponse;
import com.pt.library.response.Response;
import com.pt.library.response.SearchBookResponse;
/**
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public interface IBookService {
	public CreateBookResponse createBook(String bookName, String author, int numberOfCopies);
	public Response deleteBook(String bookName);
	public ListBookResponse listAllBooks();
	public SearchBookResponse findBookByName(String bookName);
}
