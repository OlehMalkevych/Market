package com.logos.market.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequestDTO {

	@NotBlank
	private String login;

	@Size(min = 3, max = 30)
	private String password;

}
