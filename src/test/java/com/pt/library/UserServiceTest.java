package com.pt.library;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.pt.library.das.entity.SubscriptionType;
import com.pt.library.das.entity.User;
import com.pt.library.dto.BookIssuedDetails;
import com.pt.library.dto.UserDetails;
import com.pt.library.response.CreateUserResponse;
import com.pt.library.response.ListUserResponse;
import com.pt.library.response.SearchUserReponse;
import com.pt.library.service.IUserService;
import com.pt.library.service.impl.UserServiceImpl;
/**
 * 
 * @author pranav.tiwary@vuclip.com
 *
 */
public class UserServiceTest {

	private IUserService service = null;

	@Before
	public void setup(){
		service = new UserServiceImpl();
	}

	@After
	public void tearDown(){

	}

	@Test
	public void testUser(){
		String username="testing";
		User user = new User(username, "address", SubscriptionType.MONTHLY);
		CreateUserResponse createUserResponse = service.saveUser(user);
		assertNotNull(createUserResponse);
		assertTrue(createUserResponse.isSuccess());
		UserDetails userDetails = createUserResponse.getUserDetails();
		assertNotNull(userDetails);
		assertEquals(userDetails.getUserName(), username);
		SearchUserReponse response = service.findUserByName("testing");
		assertNotNull(response);
		assertTrue(response.isSuccess());
		userDetails = response.getUserDetails();
		assertNotNull(userDetails);
		assertEquals(userDetails.getUserName(), username);
		List<BookIssuedDetails> issuedBookDetails = userDetails.getIssueDetails();
		assertNotNull(issuedBookDetails);
		assertEquals(issuedBookDetails.size(), 0);
		boolean ifExists=service.checkIfNameTaken(username);
		assertTrue(ifExists);
		ListUserResponse listUserResponse = service.listAllUser();
		assertNotNull(listUserResponse);
		assertTrue(listUserResponse.isSuccess());
		List<UserDetails> users = listUserResponse.getUsers();
		assertNotNull(users);
		UserDetails details = users.get(0);
		assertNotNull(details);
		assertEquals(details.getUserName(), username);
	}

}
