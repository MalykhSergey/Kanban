package com.example.kanban.repository;

import com.example.kanban.entity.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpaceRepository extends JpaRepository<Space, Integer> {
    @Query(value = "SELECT * FROM users_spaces WHERE ?1", nativeQuery = true)
    List<Space> findAllByUserId(int userId);
}
