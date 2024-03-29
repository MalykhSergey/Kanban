package com.example.kanban.repository;

import com.example.kanban.entity.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpaceRepository extends JpaRepository<Space, Integer> {
    @Query(value = "SELECT * FROM users_spaces WHERE user_id=?1 AND space_id = ?2", nativeQuery = true)
    List<Object[]> findUserInSpace(int userId, int spaceId);

    @Query(value = "SELECT * FROM users_spaces WHERE space_id = ?1", nativeQuery = true)
    List<Object[]> findUsersInSpace(int spaceId);

    @Query(value = "SELECT * FROM spaces where id in " +
            "(SELECT space_id FROM users_spaces WHERE user_id = ?1)", nativeQuery = true)
    List<Space> findAllByUserId(int userId);

    @Modifying
    @Query(value = "INSERT INTO users_spaces values (?1,?2)", nativeQuery = true)
    void saveUsersSpace(int userId, int spaceId);

    @Modifying
    @Query(value = "DELETE FROM users_spaces WHERE user_id = ?1 AND space_id = ?2", nativeQuery = true)
    void deleteUsersSpace(int userId, int spaceId);

}
