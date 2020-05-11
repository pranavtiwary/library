package com.pt.library.ui;

import com.pt.library.response.CreateBookResponse;
import com.pt.library.response.ListBookResponse;
import com.pt.library.response.SearchBookResponse;
import com.pt.library.service.IBookService;
import com.pt.library.service.impl.BookServiceImpl;
import com.pt.library.utility.IOUtility;
/**
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class BookFlow {

	public static final String BOOK_FLOW="Welcome to Add Book\n";
	public static final String BOOK_CREATE_MSG="Please enter Book Name\n";
	public static final String BOOK_CREATE_AUTHRO_MSG="Please enter Author\n";
	public static final String BOOK_CREATE_NUM_MSG="Please enter Number of Copies\n";
	public static final String BOOK_CREATE_SUCCESS="Created Successfully\n";
	public static final String BOOK_DISPLAY_MSG = "Displaying Book :\n";
	public static final String BOOK_STATUS_MSG = "Displaying book status :\n";

	private IBookService bookService;

	public BookFlow(){
		bookService=new BookServiceImpl();
	}

	public void startCreateBookFlow(){
		IOUtility.writeOnConsole(BOOK_FLOW);
		String bookName=IOUtility.readLineUntil(BOOK_CREATE_MSG);
		String author=IOUtility.readLineUntil(BOOK_CREATE_AUTHRO_MSG);
		int numberOfCopies=IOUtility.readIntegerUntil(1, 999999, BOOK_CREATE_NUM_MSG);
		CreateBookResponse createBook = bookService.createBook(bookName, author, numberOfCopies);
		if(createBook.isSuccess()){
			IOUtility.writeOnConsole(BOOK_CREATE_SUCCESS);
		}else{
			IOUtility.writeOnConsole(createBook.getMessage());
		}
		IOUtility.writeOnConsole("\n\n");
	}

	public void displayBooks() {
		IOUtility.writeOnConsole(BOOK_DISPLAY_MSG);
		ListBookResponse listAllBooks = bookService.listAllBooks();
		listAllBooks.getBooks().stream().forEach(System.out::println);
		IOUtility.writeOnConsole("\n\n");
	}

	public void displayBookStatus() {
		IOUtility.writeOnConsole(BOOK_STATUS_MSG);
		String name=IOUtility.readLineUntil(BOOK_CREATE_MSG);
		SearchBookResponse bookByName = bookService.findBookByName(name);
		if(bookByName.isSuccess()){
			IOUtility.writeOnConsole(bookByName.getBookDetails().toString());
		}else{
			IOUtility.writeOnConsole(bookByName.getMessage());
		}
	}
}