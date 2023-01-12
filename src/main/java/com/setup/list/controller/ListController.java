package com.setup.list.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.setup.list.dto.User;
import com.setup.list.dto.Users;
import com.setup.servicecall.DetailsServiceCaller;

@RestController
@RequestMapping("/setup/list/")
public class ListController {
	
	@Autowired
	DetailsServiceCaller detailsServiceCaller;
	
	// http://localhost:8078/setup/list/users
	@GetMapping(value="users")
	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		users.add(User.builder()
				.id(12)
				.username("dks")
				.email("dks@email.com")
				.profile("Test profile")
				.mob("+00 9899120193")
				.build());
		users.add(User.builder()
				.id(14)
				.username("kjn")
				.email("kjn@email.com")
				.profile("Test profile")
				.mob("+00 9899140194")
				.build());
		users.add(User.builder()
				.id(27)
				.username("pcy")
				.email("pcy@email.com")
				.profile("Test profile")
				.mob("+00 9899271192")
				.build());
		
		users.add(detailsServiceCaller.getUserDetails());
		return users;
	}
	
	@GetMapping(value="db-users")
	public List<Users> getDbUsers(){
		return detailsServiceCaller.getAllDbUsers();
	}
	
}
