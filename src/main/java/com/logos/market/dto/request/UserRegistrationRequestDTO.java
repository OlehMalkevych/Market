package com.logos.market.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequestDTO {

    private String login;

    private String password;

    private String username;
}
