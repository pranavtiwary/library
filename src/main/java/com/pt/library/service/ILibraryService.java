package com.pt.library.service;

import com.pt.library.response.Response;

public interface ILibraryService {

	Response issueBook(String userName, String bookName);

	Response returnBook(String userName, String bookName);

}

