package com.pt.library.ui;

import com.pt.library.response.Response;
import com.pt.library.service.ILibraryService;
import com.pt.library.service.impl.LibraryServiceImpl;
import com.pt.library.utility.IOUtility;
/**
 * Library flow
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class LibraryFlow {
	public static final String LIB_FLOW_MENU="Press 1 to Issue a Book\nPress 2 to Return a book\nPress 3 for main menu\n";
	public static final String LIB_ISSUE_BOOK_USER_NAME_MSG="Please enter user name\n";
	public static final String LIB_ISSUE_BOOK_BOOK_NAME_MSG="Please enter book name\n";

	ILibraryService service=null;
	public LibraryFlow(){
		service=new LibraryServiceImpl();
	}

	public void startLibrary(){
		int readIntegerUntil = IOUtility.readIntegerUntil(1, 3, LIB_FLOW_MENU);
		String userName, bookName;
		switch(readIntegerUntil){
		case 3:
			break;
		case 1 :
			userName = IOUtility.readLineUntil("Please enter user name\n");
			bookName = IOUtility.readLineUntil("Please enter book name\n");
			Response issueBook = service.issueBook(userName, bookName);
			IOUtility.writeOnConsole(issueBook.getMessage());
			break;
		case 2 :
			userName = IOUtility.readLineUntil("Please enter user name\n");
			bookName = IOUtility.readLineUntil("Please enter book name\n");
			issueBook = service.returnBook(userName, bookName);
			IOUtility.writeOnConsole(issueBook.getMessage());
			break;
		}
		IOUtility.writeOnConsole("\n\n");
	}

}
