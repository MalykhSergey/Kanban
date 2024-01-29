package com.example.kanban.repository;

import com.example.kanban.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);

    @Query(value = "SELECT users.id, users.name, users.password " +
            "FROM users_spaces INNER JOIN users  on users.id = users_spaces.user_id " +
            "WHERE space_id = ?1", nativeQuery = true)
    List<User> findAllUsersBySpaceId(int spaceId);

}
