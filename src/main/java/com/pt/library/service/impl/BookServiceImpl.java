package com.pt.library.service.impl;

import java.util.List;

import com.pt.library.das.entity.Book;
import com.pt.library.das.entity.Book2User;
import com.pt.library.das.repo.Book2UserRepository;
import com.pt.library.das.repo.BookRepository;
import com.pt.library.das.repo.IBook2UserRepository;
import com.pt.library.das.repo.IBookRepository;
import com.pt.library.dto.BookDetails;
import com.pt.library.dto.BookIssuedDetails;
import com.pt.library.response.CreateBookResponse;
import com.pt.library.response.ListBookResponse;
import com.pt.library.response.Response;
import com.pt.library.response.SearchBookResponse;
import com.pt.library.service.IBookService;

public class BookServiceImpl implements IBookService{

	private IBookRepository repo;

	private IBook2UserRepository bookToUserRepo;

	public BookServiceImpl(){
		this.repo=new BookRepository();
		this.bookToUserRepo= new Book2UserRepository();
	}

	@Override
	public CreateBookResponse createBook(String bookName, String author, int numberOfCopies) {
		CreateBookResponse response=null;
		try{
			Book book = repo.findBookByName(bookName);
			if(null!=book){
				book.incrementAvailabilityCount();
			}else{
				book= new Book(bookName, author,numberOfCopies);
			}
			repo.saveBook(book);
			response= new CreateBookResponse(true, "Success");
			response.setBookDetails(new BookDetails(book.getName(), book.getAvailabiltyCount()));
		}catch(Exception ex){
			response = new CreateBookResponse(false, "App Error");
		}
		return response;
	}

	@Override
	public Response deleteBook(String bookName) {
		Response response=null;
		try{
			repo.deleteBook(bookName);
			response= new Response(true, "Success");
		}catch(Exception ex){
			response = new Response(false, "App Error");
		}
		return response;
	}

	@Override
	public ListBookResponse listAllBooks() {
		ListBookResponse response=null;
		try{
			response = new ListBookResponse(true, "Success");
			List<Book> list = repo.findAll();
			for (Book book : list) {
				BookDetails bookDetails= new BookDetails(book.getName(), book.getAvailabiltyCount());
				List<Book2User> book2issuedUsers = bookToUserRepo.findIssuedBookDetails(book.getName());
				if(null!=book2issuedUsers)
				for (Book2User book2User : book2issuedUsers) {
					bookDetails.getIssueDetails().add(new BookIssuedDetails(book2User.getUserName(), book.getName(),
							book2User.getLendOn(), book2User.getReturnedOn()));
				}
				response.getBooks().add(bookDetails);
			}
		}catch(Exception ex){
			response = new ListBookResponse(false, "App Error");
		}
		return response;
	}

	@Override
	public SearchBookResponse findBookByName(String bookName) {
		SearchBookResponse response = null;
		try{
			response=new SearchBookResponse(true, "Success");
			Book book = repo.findBookByName(bookName);
			if(null!=book){
				BookDetails bookDetails= new BookDetails(book.getName(), book.getAvailabiltyCount());
				List<Book2User> book2issuedUsers = bookToUserRepo.findIssuedBookDetails(book.getName());
				if(null!=book2issuedUsers)
					for (Book2User book2User : book2issuedUsers) {
						bookDetails.getIssueDetails().add(new BookIssuedDetails(book2User.getUserName(), book.getName(),
								book2User.getLendOn(), book2User.getReturnedOn()));
					}
				response.setBookDetails(bookDetails);
			}else{
				response = new SearchBookResponse(false, "Book Not found");
			}
		}catch(Exception ex){
			response = new SearchBookResponse(false, "App Error");
		}
		return response;
	}


}
