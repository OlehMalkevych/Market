package com.logos.market.service.serviceImpl;

import com.logos.market.domain.Cart;
import com.logos.market.domain.User;
import com.logos.market.domain.UserRole;
import com.logos.market.dto.request.UserLoginRequestDTO;
import com.logos.market.dto.request.UserRegistrationRequestDTO;
import com.logos.market.dto.response.AuthenticationResponseDTO;
import com.logos.market.repository.UserRepository;
import com.logos.market.security.JwtTokenTool;
import com.logos.market.security.JwtUser;
import com.logos.market.service.ServiceInt.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    //TODO 14
    private AuthenticationManager authenticationManager;

    private JwtTokenTool jwtTokenTool;

    private BCryptPasswordEncoder encoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           AuthenticationManager authenticationManager,
                           JwtTokenTool jwtTokenTool,
                           BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenTool = jwtTokenTool;
        this.encoder = encoder;
    }

    // TODO 15
    @Override
    public AuthenticationResponseDTO registerUser(UserRegistrationRequestDTO request) {
        if (userRepository.existsByLogin(request.getLogin())) {
            throw new BadCredentialsException(
                    "User with login " + request.getLogin() + " already exists");
        }
        User user = new User();
        user.setLogin(request.getLogin());
        user.setUserRole(UserRole.ROLE_USER);
        user.setPassword(encoder.encode(request.getPassword()));
        user.setUsername(request.getUsername());
        user.setCart(new Cart());

        userRepository.save(user);

        return login(mapRegistrationToLogin(request));
    }

    // TODO 16
    @Override
    public AuthenticationResponseDTO login(UserLoginRequestDTO request) {
        String login = request.getLogin();
        User user = findByLogin(login);

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, request.getPassword()));

        String token = jwtTokenTool.createToken(login, user.getUserRole());
        String name = user.getUsername();
        Long id = user.getId();

        return new AuthenticationResponseDTO(name, token, id);
    }

    private UserLoginRequestDTO mapRegistrationToLogin(UserRegistrationRequestDTO registration) {
        return new UserLoginRequestDTO(registration.getLogin(), registration.getPassword());
    }

    @Override
    public User findByLogin(String username) {
        return userRepository.findByLogin(username);
    }

    @Override
    public User getCurrentUser() {
        return findByLogin((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        return new JwtUser(user.getLogin(), user.getUserRole(), user.getPassword());
    }
}
