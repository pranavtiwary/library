package com.pt.library.das.repo;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.pt.library.das.entity.Book;

/**
 * User repository
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class BookRepository implements IBookRepository {

	private static final HashMap<String, Book> bookData = new HashMap<>();

	@Override
	public Book saveBook(Book book){
		bookData.put(book.getName(), book);
		return book;
	}

	@Override
	public Book findBookByName(String  bookName) {
		return bookData.get(bookName);
	}

	@Override
	public void deleteBook(String bookName) {
		bookData.remove(bookName);
	}

	@Override
	public List<Book> findAll() {
		List<Book> books = bookData.values().stream()
				.collect(Collectors.toList());
		return books;
	}

}
