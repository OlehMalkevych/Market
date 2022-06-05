package com.logos.market.dto.response;

import lombok.Getter;
import lombok.Setter;

// TODO 13
@Getter
@Setter
public class AuthenticationResponseDTO {

	private String username;
	private Long id;
	private String token;

	public AuthenticationResponseDTO(String username, String token, Long id) {
		this.username = username;
		this.token = token;
		this.id = id;
	}

}
