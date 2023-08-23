package com.example.kanban.controller;

import com.example.kanban.entity.User;
import com.example.kanban.result.Result;
import com.example.kanban.result.UserResult;
import com.example.kanban.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String createUser(HttpServletResponse httpServletResponse, @RequestBody User user) {
        Result result = userService.createUser(user);
        if (result != UserResult.UserCreated)
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return result.getMessage();
    }
}
