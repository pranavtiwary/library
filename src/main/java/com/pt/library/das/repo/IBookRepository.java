package com.pt.library.das.repo;

import java.util.List;

import com.pt.library.das.entity.Book;

public interface IBookRepository {

	Book saveBook(Book user);

	Book findBookByName(String bookName);
	
	void deleteBook(String bookName);

	List<Book> findAll();
}
