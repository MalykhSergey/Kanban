package com.example.kanban.service;

import com.example.kanban.entity.User;
import com.example.kanban.repository.UserRepository;
import com.example.kanban.result.Result;
import com.example.kanban.result.UserResult;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Result createUser(User user) {
        if (user.getUsername() == null || user.getUsername().length() < 5)
            return UserResult.UserCreated;
        if (user.getPassword() == null || user.getPassword().length() < 5)
            return UserResult.TooShortPassword;
        if (userRepository.findByName(user.getUsername()) != null)
            return UserResult.UserIsExists;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return UserResult.UserCreated;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username);
        if (user == null)
            throw new UsernameNotFoundException("Invalid credentials");
        return user;
    }
}
