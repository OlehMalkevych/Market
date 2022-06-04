package com.logos.market.service.ServiceImpl;

import com.logos.market.domain.Cart;
import com.logos.market.domain.User;
import com.logos.market.dto.request.UserRequestDTO;
import com.logos.market.repository.UserRepository;
import com.logos.market.service.ServiceInt.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(UserRequestDTO requestDTO) {
        if (userRepository.existsByLogin(requestDTO.getLogin())) {
            throw new IllegalArgumentException("User with login " + requestDTO.getLogin() + "already exists");
        }
        return userRepository.save(mapUserRequestDTOToUser(requestDTO));
    }

    private User mapUserRequestDTOToUser(UserRequestDTO requestDTO){
        User user = new User();
        user.setPassword(requestDTO.getPassword());
        user.setLogin(requestDTO.getLogin());
        user.setUsername(requestDTO.getUsername());
        user.setCart(new Cart());

        return user;
    }

}
