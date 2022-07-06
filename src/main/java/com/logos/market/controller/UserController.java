package com.logos.market.controller;


import com.logos.market.dto.request.UserLoginRequestDTO;
import com.logos.market.dto.request.UserRegistrationRequestDTO;
import com.logos.market.dto.response.AuthenticationResponseDTO;
import com.logos.market.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

	private UserServiceImpl userServiceImpl;

	@Autowired
	public UserController(UserServiceImpl userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

	@PostMapping("/register")
	public AuthenticationResponseDTO registerUser(@RequestBody UserRegistrationRequestDTO requestDTO) {
		return userServiceImpl.registerUser(requestDTO);
	}

	@PostMapping("/login")
	public AuthenticationResponseDTO login(@RequestBody UserLoginRequestDTO requestDTO) {
		return userServiceImpl.login(requestDTO);
	}

}
