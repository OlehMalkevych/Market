package com.logos.market.controller;


import com.logos.market.dto.request.UserRequestDTO;
import com.logos.market.dto.response.UserResponseDTO;
import com.logos.market.service.ServiceImpl.UserServiceImpl;
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

	//	// localhost:8080/users/register
	@PostMapping("/register")
	public UserResponseDTO registerUser(@RequestBody UserRequestDTO requestDTO) {
		return new UserResponseDTO(userServiceImpl.registerUser(requestDTO));
	}

}
