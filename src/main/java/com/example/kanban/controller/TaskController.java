package com.example.kanban.controller;

import com.example.kanban.entity.Task;
import com.example.kanban.entity.User;
import com.example.kanban.result.TaskResult;
import com.example.kanban.service.TaskService;
import jakarta.servlet.http.HttpServletResponse;
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
    public String createTask(@AuthenticationPrincipal User user, @RequestBody Task task, HttpServletResponse httpServletResponse) {
        TaskResult taskResult = taskService.createTask(user.getId(), task);
        if (taskResult != TaskResult.TaskCreated)
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return taskResult.getMessage();
    }
    @PutMapping
    public String updateTask(@AuthenticationPrincipal User user, @RequestBody Task task, HttpServletResponse httpServletResponse){
        TaskResult taskResult = taskService.updateTask(user.getId(), task);
        if (taskResult != TaskResult.TaskUpdated)
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return taskResult.getMessage();
    }

    @DeleteMapping String deleteTask(@AuthenticationPrincipal User user, @RequestBody Task task, HttpServletResponse httpServletResponse){
        TaskResult taskResult = taskService.deleteTask(user.getId(), task);
        if (taskResult != TaskResult.TaskDeleted)
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return taskResult.getMessage();
    }
}
