package com.setup.list.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users {
	int userId;
	String name;
	String email;
	String number;
	String status;
	String password;
	Timestamp createdOn;
	Timestamp birthdate;
}