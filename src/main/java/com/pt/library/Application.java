package com.pt.library;

import com.pt.library.ui.BookFlow;
import com.pt.library.ui.LibraryFlow;
import com.pt.library.ui.UserFlow;
import com.pt.library.utility.IOUtility;
/**
 * Main Entry class
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class Application {

	public static void main(String[] args){
		IOUtility.writeOnConsole("Welcome to Library Management System\n");
		try{
			Application app= new Application();
			app.launchApp();
		}catch(Exception ex){
			System.out.println("Error in App");
			ex.printStackTrace();
		}
	}

	private UserFlow userFlow;
	private BookFlow bookFlow;
	private LibraryFlow libraryFlow;


	public Application(){
		userFlow=new UserFlow();
		bookFlow=new BookFlow();
		libraryFlow=new LibraryFlow();
	}

	public static final String MAIN_APP_MSG="Press 1 to create user profile\n"
			+ "Press 2 to add books\n"
			+ "Press 3 to display all books\n"
			+ "Press 4 to Display all users\n"
			+ "Press 5 to Issue/Return books\n"
			+ "Press 6 to user status\n"
			+ "Press 7 to Book status\n"
			+ "Press 8 to quit"
			+ "\n\n";

	private void launchApp() {
		int mode = IOUtility.readIntegerUntil(1, 8, MAIN_APP_MSG);
		switch(mode){
		case 1:
			userFlow.startCreateUserFlow();
			break;
		case 2:
			bookFlow.startCreateBookFlow();
			break;
		case 3:
			bookFlow.displayBooks();
			break;
		case 4:
			userFlow.displayUser();
			break;
		case 5:
			libraryFlow.startLibrary();
			break;
		case 6:
			userFlow.displayUserStatus();
			break;
		case 7:
			bookFlow.displayBookStatus();
			break;
		case 8:
			System.exit(0);
		default :
			IOUtility.writeOnConsole("Menu not selected");
		}
		launchApp();
	}
}