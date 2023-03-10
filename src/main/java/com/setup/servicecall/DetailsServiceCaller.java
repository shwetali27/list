package com.setup.servicecall;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.setup.list.dto.User;
import com.setup.list.dto.Users;
import com.setup.list.proxy.DetailsProxyServer;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@Component
public class DetailsServiceCaller {

	@Autowired
	private RestTemplate restTemplate;

	static final String DETAILS_BASEURL = "lb://details";
	private static final String DETAILS_SERVICE = "detailsService";
	
	int retryCount = 1;
// ------------------ CircuitBreaker 
	 @CircuitBreaker(name = DETAILS_SERVICE, fallbackMethod = "fallBackDetailsMethod")
	 
// ------------------ Retry (we can skip fallback method if not required.)
	 @Retry(name = DETAILS_SERVICE, fallbackMethod = "fallBackDetailsMethod")
	 
// ------------------ Rate Limiter -------------
//	@RateLimiter(name = DETAILS_SERVICE)
	public User getUserDetails() {
		System.out.println("Retry call count " + retryCount++ +" "+ new Date());
		User user = null;
		user = restTemplate.getForObject(DETAILS_BASEURL + "/setup/details/user", User.class);
		return user;
	}
	 
	public User fallBackDetailsMethod(Exception e) {
		return User.builder().id(00)
				.username("fallback user")
				.email("fbu@email.com")
				.profile("Fallback user profile")
				.mob("+00 9899000000")
				.build();
	}


	@Autowired
	private DetailsProxyServer detailsProxyServer;
	@CircuitBreaker(name = DETAILS_SERVICE, fallbackMethod = "fallBackListMethod")
//	@Retry(name = DETAILS_SERVICE, fallbackMethod = "fallBackListMethod")
	public List<Users> getAllDbUsers(){
		return detailsProxyServer.getAllUsers();
	}
	
	public List<Users> fallBackListMethod(Exception e) {
		List<Users> users = new ArrayList<Users>();
		users.add(Users.builder().userId(00)
				.name("fallback user")
				.email("fbu@email.com")
				.birthdate(null)
				.createdOn(Timestamp.valueOf(LocalDateTime.now()))
				.build());
		return users;
	}
}
