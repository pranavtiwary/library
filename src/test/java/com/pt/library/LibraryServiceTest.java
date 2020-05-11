package com.pt.library;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.pt.library.das.entity.SubscriptionType;
import com.pt.library.das.entity.User;
import com.pt.library.dto.BookDetails;
import com.pt.library.dto.BookIssuedDetails;
import com.pt.library.dto.UserDetails;
import com.pt.library.response.CreateBookResponse;
import com.pt.library.response.CreateUserResponse;
import com.pt.library.response.Response;
import com.pt.library.response.SearchBookResponse;
import com.pt.library.response.SearchUserReponse;
import com.pt.library.service.IBookService;
import com.pt.library.service.ILibraryService;
import com.pt.library.service.IUserService;
import com.pt.library.service.impl.BookServiceImpl;
import com.pt.library.service.impl.LibraryServiceImpl;
import com.pt.library.service.impl.UserServiceImpl;

public class LibraryServiceTest {

	private IUserService userService = null;
	private ILibraryService libService=null;
	private IBookService bookService=null;

	@Before
	public void setup(){
		userService = new UserServiceImpl();
		libService = new LibraryServiceImpl(); 
		bookService = new BookServiceImpl();
	}

	@After
	public void testDown(){

	}
	
	@Test
	public void testIssueBook2User(){
		String userName="testing-user";
		User user = new User(userName, "address", SubscriptionType.MONTHLY);
		CreateUserResponse createUserResponse = userService.saveUser(user);
		assertNotNull(createUserResponse);
		assertTrue(createUserResponse.isSuccess());
		String bookName="text-book";
		CreateBookResponse book = bookService.createBook(bookName, "test-author", 5);
		assertNotNull(book);
		assertTrue(book.isSuccess());
		Response response = libService.issueBook(userName, bookName);
		assertNotNull(response);
		assertTrue(response.isSuccess());
		SearchBookResponse findBookByName = bookService.findBookByName(bookName);
		assertNotNull(findBookByName);
		assertTrue(findBookByName.isSuccess());
		BookDetails bookDetails = findBookByName.getBookDetails();
		assertNotNull(bookDetails);
		assertEquals(bookDetails.getBookName(), bookName);
		int availabilityCount = bookDetails.getAvailabilityCount();
		assertEquals(availabilityCount, 4);
		List<BookIssuedDetails> issueDetails = bookDetails.getIssueDetails();
		assertNotNull(issueDetails);
		assertEquals(issueDetails.size(), 1);
		BookIssuedDetails bookIssuedDetails = issueDetails.get(0);
		assertEquals(bookIssuedDetails.getUserName(),userName);
		Response returnBook = libService.returnBook(userName, bookName);
		assertNotNull(returnBook);
		assertTrue(returnBook.isSuccess());
		findBookByName = bookService.findBookByName(bookName);
		assertNotNull(findBookByName);
		assertTrue(findBookByName.isSuccess());
		bookDetails = findBookByName.getBookDetails();
		assertNotNull(bookDetails);
		assertEquals(bookDetails.getBookName(), bookName);
		availabilityCount = bookDetails.getAvailabilityCount();
		assertEquals(availabilityCount, 5);
		issueDetails = bookDetails.getIssueDetails();
		assertNotNull(issueDetails);
		assertEquals(issueDetails.size(), 1);
		assertEquals(bookIssuedDetails.getUserName(),userName);
		SearchUserReponse findUserByName = userService.findUserByName(userName);
		assertNotNull(findUserByName);
		assertTrue(findUserByName.isSuccess());
		UserDetails userDetails = findUserByName.getUserDetails();
		assertNotNull(userDetails);
		List<BookIssuedDetails> issueDetails2 = userDetails.getIssueDetails();
		assertNotNull(issueDetails2);
		assertEquals(issueDetails2.size(),0);
	}

}
