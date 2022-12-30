package com.setup.list.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
	public int id;
	public String username;
	public String email;
	public String profile;
	public String mob;
}
