package com.pt.library.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.pt.library.das.entity.Book2User;
import com.pt.library.das.entity.User;
import com.pt.library.das.repo.Book2UserRepository;
import com.pt.library.das.repo.IBook2UserRepository;
import com.pt.library.das.repo.IUserRepository;
import com.pt.library.das.repo.UserRepository;
import com.pt.library.dto.BookIssuedDetails;
import com.pt.library.dto.UserDetails;
import com.pt.library.response.CreateUserResponse;
import com.pt.library.response.ListUserResponse;
import com.pt.library.response.Response;
import com.pt.library.response.SearchBookResponse;
import com.pt.library.response.SearchUserReponse;
import com.pt.library.service.IUserService;

/**
 * User service
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class UserServiceImpl implements IUserService {

	private IUserRepository repo;
	private IBook2UserRepository book2UserRepo;

	public UserServiceImpl(){
		this.repo= new UserRepository();
		this.book2UserRepo=new Book2UserRepository();
	}

	@Override
	public boolean checkIfNameTaken(String name) {
		return repo.findUserByName(name)==null?false:true;
	}

	@Override
	public CreateUserResponse saveUser(User user) {
		CreateUserResponse response=null;
		try{
			response= new CreateUserResponse(true, "Success");
			User savedUser = repo.saveUser(user);
			UserDetails userDetails = new UserDetails(savedUser.getName(), savedUser.getAddress());
			response.setUserDetails(userDetails);
		}catch(Exception ex){
			response =  new CreateUserResponse(false, "App Error");
		}
		return response;
	}

	@Override
	public Response deleteUser(String userName) {
		Response response=null;
		try{
			response=new Response(true, "Success");
			repo.deleteuser(userName);
		}catch(Exception ex){
			response =  new Response(false, "App Error");
		}
		return response;
	}

	@Override
	public SearchUserReponse findUserByName(String userName) {
		SearchUserReponse response=null;
		try{
			response=new SearchUserReponse(true, "Success");
			User user = repo.findUserByName(userName);
			if(null!=user){
				UserDetails userDetails= new UserDetails(user.getName(), user.getAddress());
				List<String> issuedBooks = user.getIssuedBooks();
				for (String bookName : issuedBooks) {
					List<Book2User> findBook = book2UserRepo.findIssuedBookDetails(bookName);
					Book2User book2User = findBook.stream().filter(e->e.getUserName().equalsIgnoreCase(userName)).findAny().get();
					BookIssuedDetails details = new BookIssuedDetails(userName, bookName, book2User.getLendOn(), book2User.getReturnedOn());
					userDetails.getIssueDetails().add(details);
				}
				response.setUserDetails(userDetails);
			}else{
				response = new SearchUserReponse(false, "User Not found");
			}
		}catch(Exception ex){
			response =  new SearchUserReponse(false, "App Error");
		}
		return response;
	}

	@Override
	public ListUserResponse listAllUser() {
		ListUserResponse response=null;
		try{
			response=new ListUserResponse(true, "Success");
			List<User> allUsers = repo.findAll();
			List<UserDetails> users= new ArrayList<>();
			allUsers.stream().forEach(e->{
				UserDetails userDetails= new UserDetails(e.getName(), e.getAddress());
				List<String> issuedBooks = e.getIssuedBooks();
				for (String bookName : issuedBooks) {
					List<Book2User> findBook = book2UserRepo.findIssuedBookDetails(bookName);
					Book2User book2User = findBook.stream().filter(b->b.getUserName().equalsIgnoreCase(e.getName())).findAny().get();
					BookIssuedDetails details = new BookIssuedDetails(e.getName(), bookName, book2User.getLendOn(), book2User.getReturnedOn());
					userDetails.getIssueDetails().add(details);
				}
				users.add(userDetails);
			});
			response.getUsers().addAll(users);
		}catch(Exception ex){
			response =  new ListUserResponse(false, "App Error");
		}
		return response;
	}
}
