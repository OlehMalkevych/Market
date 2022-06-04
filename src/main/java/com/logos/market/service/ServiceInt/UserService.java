package com.logos.market.service.ServiceInt;

import com.logos.market.domain.User;
import com.logos.market.dto.request.UserRequestDTO;

public interface UserService {

    User registerUser(UserRequestDTO requestDTO);

}
