package com.logos.market.service.ServiceInt;

import com.logos.market.domain.User;
import com.logos.market.dto.request.UserLoginRequestDTO;
import com.logos.market.dto.request.UserRegistrationRequestDTO;
import com.logos.market.dto.response.AuthenticationResponseDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    AuthenticationResponseDTO registerUser(UserRegistrationRequestDTO requestDTO);

    AuthenticationResponseDTO login(UserLoginRequestDTO requestDTO);

    User findByLogin(String username);

    User getCurrentUser();
}