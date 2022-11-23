package com.vgnshiyer.blog.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vgnshiyer.blog.api.exceptions.InvalidCredentialException;
import com.vgnshiyer.blog.api.payloads.JwtAuthRequest;
import com.vgnshiyer.blog.api.payloads.JwtAuthResponse;
import com.vgnshiyer.blog.api.payloads.UserDto;
import com.vgnshiyer.blog.api.services.UserService;
import com.vgnshiyer.blog.api.uitls.JwtTokenHelper;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception{
		this.authenticate(request.getUsername(), request.getPassword());
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		String token = this.jwtTokenHelper.generateToken(userDetails);
		
		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(token);
		return ResponseEntity.ok(response);
	}
	
	private void authenticate(String username, String password) throws Exception {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		try {
		this.authenticationManager.authenticate(authenticationToken);
		} catch(BadCredentialsException e) {
			System.out.println("Invalid credentials");
			throw new InvalidCredentialException();
		}
	}

	@PostMapping("/register")
	private ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
		UserDto user = this.userService.registerUser(userDto);
		return new ResponseEntity<UserDto>(user, HttpStatus.CREATED);
	}
}
