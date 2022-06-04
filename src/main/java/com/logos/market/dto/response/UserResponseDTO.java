package com.logos.market.dto.response;

import lombok.Getter;
import lombok.Setter;
import com.logos.market.domain.User;


@Getter
@Setter
public class UserResponseDTO {

    private String userName;

    public UserResponseDTO(User user) {
        this.userName = user.getUsername();
    }
}
