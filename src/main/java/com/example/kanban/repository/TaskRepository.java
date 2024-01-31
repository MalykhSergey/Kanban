package com.example.kanban.repository;

import com.example.kanban.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query(value = "SELECT * FROM tasks WHERE space_id = ?1", nativeQuery = true)
    List<Task> findAllBySpaceId(int spaceId);

    void deleteAllBySpaceId(int spaceId);

}
