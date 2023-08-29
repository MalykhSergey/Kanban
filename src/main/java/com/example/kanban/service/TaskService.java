package com.example.kanban.service;

import com.example.kanban.entity.Task;
import com.example.kanban.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final SpaceService spaceService;

    @Autowired
    public TaskService(TaskRepository taskRepository, SpaceService spaceService) {
        this.taskRepository = taskRepository;
        this.spaceService = spaceService;
    }

    public List<Task> getTasksByUserIdAndSpaceId(int userId, int spaceId) {
        if (spaceService.checkUserExistsInSpace(userId, spaceId)) {
            return taskRepository.findAllBySpaceId(spaceId);
        } else return new ArrayList<>();
    }
}