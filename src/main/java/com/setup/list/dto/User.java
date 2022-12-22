package com.setup.list.dto;

import lombok.Builder;

@Builder
public class User {
	public int id;
	public String username;
	public String email;
	public String profile;
	public String mob;
}
