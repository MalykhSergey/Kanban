package com.example.kanban.service;

import com.example.kanban.entity.User;
import com.example.kanban.repository.UserRepository;
import com.example.kanban.result.Result;
import com.example.kanban.result.UserResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements AuthenticationProvider {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Result createUser(User user) {
        if (user.getUsername() == null || user.getUsername().length() < 5)
            return UserResult.TooShortName;
        if (user.getPassword() == null || user.getPassword().length() < 5)
            return UserResult.TooShortPassword;
        if (userRepository.findByName(user.getUsername()) != null)
            return UserResult.UserIsExists;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return UserResult.UserCreated;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = userRepository.findByName(authentication.getName());
        if (user != null) {
            if (passwordEncoder.matches(authentication.getCredentials().toString(),user.getPassword()))
                return user;
        }
        throw new BadCredentialsException("Invalid credentials!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
