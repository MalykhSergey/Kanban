package com.example.kanban.controller;

import com.example.kanban.entity.Task;
import com.example.kanban.entity.User;
import com.example.kanban.result.TaskResult;
import com.example.kanban.service.TaskService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
        return taskService.getTasksByUserIdAndSpaceId(user.getId(), spaceId);
    }

    @PostMapping
    public String createTask(@AuthenticationPrincipal User user, @RequestBody Task task) {
        return taskService.createTask(user.getId(), task).getMessage();
    }
    @PutMapping
    public String updateTask(@AuthenticationPrincipal User user, @RequestBody Task task){
        return taskService.updateTask(user.getId(),task).getMessage();
    }
}
