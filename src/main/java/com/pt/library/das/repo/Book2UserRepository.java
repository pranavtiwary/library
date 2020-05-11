package com.pt.library.das.repo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.pt.library.das.entity.Book;
import com.pt.library.das.entity.Book2User;
import com.pt.library.das.entity.User;
import com.pt.library.exception.NeverAssignedBookException;

/**
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class Book2UserRepository implements IBook2UserRepository {

	private static final HashMap<String, List<Book2User>> book2UserData = new HashMap<>();

	IUserRepository userRepo;
	IBookRepository bookRepo;

	public Book2UserRepository(){
		userRepo=new UserRepository();
		bookRepo=new BookRepository();
	}

	@Override
	public void lendBookToUser(Book book, User user) {
		List<Book2User> list = book2UserData.get(book.getName());
		if(null==list){
			list=new ArrayList<>();
		}
		Book2User bookLended=new Book2User(user.getName(), new Date());
		list.add(bookLended);
		book.decrementAvailabilityCount();
		user.getIssuedBooks().add(book.getName());
		userRepo.saveUser(user);
		bookRepo.saveBook(book);
		book2UserData.put(book.getName(), list);
	}

	@Override
	public void returnBookToUser(Book book, User user) {
		List<Book2User> list = book2UserData.get(book.getName());
		if(null==list || list.size()==0){
			throw new NeverAssignedBookException("This book was never issues to any one");
		}
		Optional<Book2User> any = list.stream()
				.filter(e->e.getUserName().equalsIgnoreCase(user.getName())).findAny();
		if(!any.isPresent()){
			throw new NeverAssignedBookException("This book was never issued this user");
		}else{
			any.get().setReturnedOn(new Date());
		}
		book.incrementAvailabilityCount();
		user.getIssuedBooks().remove(book.getName());
		userRepo.saveUser(user);
		bookRepo.saveBook(book);
		book2UserData.put(book.getName(), list);
	}

	@Override
	public List<Book2User> findIssuedBookDetails(String bookName) {
		return book2UserData.get(bookName);
	}

}
