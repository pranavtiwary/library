package com.pt.library;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.pt.library.dto.BookDetails;
import com.pt.library.response.CreateBookResponse;
import com.pt.library.response.ListBookResponse;
import com.pt.library.response.SearchBookResponse;
import com.pt.library.service.IBookService;
import com.pt.library.service.impl.BookServiceImpl;

public class BookServiceTest {
	private IBookService service = null;

	@Before
	public void setup(){
		service = new BookServiceImpl();
	}

	@After
	public void tearDown(){

	}

	@Test
	public void testBook(){
		String bookName="book-test";
		CreateBookResponse book = service.createBook(bookName, "test-author", 5);
		assertNotNull(book);
		assertTrue(book.isSuccess());
		assertNotNull(book.getBookDetails());
		assertEquals(book.getBookDetails().getBookName(), bookName);
		ListBookResponse listAllBooks = service.listAllBooks();
		assertNotNull(listAllBooks);
		assertTrue(listAllBooks.isSuccess());
		List<BookDetails> books = listAllBooks.getBooks();
		assertNotNull(books);
		assertEquals(books.size(), 1);
		BookDetails bookDetails = books.get(0);
		assertNotNull(bookDetails);
		assertEquals(bookDetails.getBookName(), bookName);
		SearchBookResponse findBookByName = service.findBookByName(bookName);
		assertNotNull(findBookByName);
		assertTrue(findBookByName.isSuccess());
		assertNotNull(findBookByName.getBookDetails());
		assertEquals(findBookByName.getBookDetails().getBookName(), bookName);
	}
}
