package com.example.kanban.service;

import com.example.kanban.entity.User;
import com.example.kanban.repository.UserRepository;
import com.example.kanban.result.UserResult;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserServiceTest {

    @Test
    void createUser() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        UserService userService = new UserService(userRepository, Mockito.mock(PasswordEncoder.class));
        User userWithShortName = new User();
        assertEquals(userService.createUser(userWithShortName), UserResult.TooShortName);
        userWithShortName.setName("1234");
        assertEquals(userService.createUser(userWithShortName), UserResult.TooShortName);
        User userWithShortPassword = new User();
        userWithShortPassword.setName("12345");
        assertEquals(userService.createUser(userWithShortPassword), UserResult.TooShortPassword);
        userWithShortPassword.setName("12345");
        userWithShortPassword.setPassword("1234");
        assertEquals(userService.createUser(userWithShortPassword), UserResult.TooShortPassword);
        User existedUser = new User();
        existedUser.setName("Existed name");
        Mockito.when(userRepository.findByName(existedUser.getUsername())).thenReturn(existedUser);
        User correctUser = new User();
        correctUser.setName("Some name");
        correctUser.setPassword("Some password");
        assertEquals(userService.createUser(correctUser), UserResult.UserCreated);
    }
}