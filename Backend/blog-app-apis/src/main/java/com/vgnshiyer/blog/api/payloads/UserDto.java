package com.vgnshiyer.blog.api.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;
	
	@NotEmpty(message = "Username cannot be empty")
	@Size(min = 4, message = "Username must contain minimum 4 characters")
	private String name;
	
//	@JsonIgnore
	@NotEmpty(message = "Password cannot be empty")
	@Size(min = 4, max = 8, message = "Password should contain 4 to 8 characters only !")
	private String password;
	
	@Email(message = "Email address entered is not valid !!")
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String about;
	
	private Set<RoleDto> roles = new HashSet<>();
}
