package com.pt.library.service.impl;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import com.pt.library.das.entity.Book;
import com.pt.library.das.entity.User;
import com.pt.library.das.repo.Book2UserRepository;
import com.pt.library.das.repo.BookRepository;
import com.pt.library.das.repo.IBook2UserRepository;
import com.pt.library.das.repo.IBookRepository;
import com.pt.library.das.repo.IUserRepository;
import com.pt.library.das.repo.UserRepository;
import com.pt.library.exception.NeverAssignedBookException;
import com.pt.library.response.Response;
import com.pt.library.service.ILibraryService;

/**
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class LibraryServiceImpl implements ILibraryService {

	private IBook2UserRepository book2UserRepo;

	private IUserRepository userRepo;

	private IBookRepository bookRepo;

	public LibraryServiceImpl(){
		book2UserRepo=new Book2UserRepository();
		userRepo=new UserRepository();
		bookRepo=new BookRepository();
	}

	Map<String, String> globalLockMap = new ConcurrentHashMap<>();

	@Override
	public Response issueBook(String userName, String bookName) {

		while(globalLockMap.get(bookName)!=null){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		globalLockMap.put(bookName, bookName);

		Response response = null;
		try{
			User user = userRepo.findUserByName(userName);
			if(null==user){
				response=new Response(false,"User Doesn't exists");
				return response;
			}
			Book book = bookRepo.findBookByName(bookName);
			if(null==book){
				response=new Response(false,"Book Not Found");
				return response;
			}
			if(book.getAvailabiltyCount()<=0){
				response=new Response(false,"Currently, Book is not available");
				return response;
			}
			if(user.getIssuedBooks().size() ==user.getSubscriptionType().getNoOfBookAllowed()){
				response=new Response(false,"User all ready have maximum book issued");
				return response;
			}
			Optional<String> allreadyIssuedBook = user.getIssuedBooks().stream().filter(e->e.equalsIgnoreCase(bookName)).findAny();
			if(allreadyIssuedBook.isPresent()){
				response=new Response(false,"Already issued to user");
				return response;
			}
			book2UserRepo.lendBookToUser(book,user);
			response=new Response(true,"Book is assigned to user");
		}catch(Exception ex){
			response=new Response(false, "Application Error");
		}
		globalLockMap.remove(bookName);
		return response;
	}

	@Override
	public Response returnBook(String userName, String bookName) {
		while(globalLockMap.get(bookName)!=null){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		globalLockMap.put(bookName, bookName);

		Response response = null;
		try{
			User user = userRepo.findUserByName(userName);
			if(null==user){
				response=new Response(false,"User Doesn't exists");
				return response;
			}
			Book book = bookRepo.findBookByName(bookName);
			if(null==book){
				response=new Response(false,"Book Not Found");
				return response;
			}
			book2UserRepo.returnBookToUser(book,user);
			response=new Response(true,"Book is returned");
		}catch(NeverAssignedBookException ex){
			response=new Response(false, ex.getMessage());
		}catch(Exception ex){
			response=new Response(false, "Application Error");
		}
		globalLockMap.remove(bookName);
		return response;
	}
}
