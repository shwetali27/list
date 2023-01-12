package com.setup.list.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.setup.config.FeignClientConfiguration;
import com.setup.list.dto.Users;

// name is same as application name given to details service
// configuration class is added since details service has spring 
// security enabled for username and password.
@FeignClient(name = "details", configuration = FeignClientConfiguration.class)
public interface DetailsProxyServer {
	@GetMapping(value = "setup/details/users")
	public List<Users> getAllUsers();
}
