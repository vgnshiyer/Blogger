package com.vgnshiyer.blog.api.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	
	@NotEmpty(message = "Password cannot be empty")
	@Size(min = 4, max = 8, message = "Password should contain 4 to 8 characters only !")
	private String password;
	
	@Email(message = "Email address entered is not valid !!")
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String about;
}
