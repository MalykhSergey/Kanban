package com.example.kanban.controller;

import com.example.kanban.entity.Task;
import com.example.kanban.entity.User;
import com.example.kanban.service.TaskService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getTasksBySpaceId(@AuthenticationPrincipal User user, @RequestParam("spaceId") int spaceId) {
        return taskService.getTasksByUserIdAndSpaceId(user.getId(),spaceId);
    }
}
