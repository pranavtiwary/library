package com.pt.library.das.repo;

import java.util.List;

import com.pt.library.das.entity.Book;
import com.pt.library.das.entity.Book2User;
import com.pt.library.das.entity.User;

public interface IBook2UserRepository {

	void lendBookToUser(Book book, User user);

	void returnBookToUser(Book book, User user);

	List<Book2User> findIssuedBookDetails(String bookName);

}

