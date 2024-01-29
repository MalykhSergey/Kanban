package com.example.kanban.service;

import com.example.kanban.entity.Space;
import com.example.kanban.entity.User;
import com.example.kanban.repository.SpaceRepository;
import com.example.kanban.repository.UserRepository;
import com.example.kanban.result.Result;
import com.example.kanban.result.SpaceResult;
import com.example.kanban.result.UserResult;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpaceService {
    private final SpaceRepository spaceRepository;
    private final UserRepository userRepository;

    @Autowired
    public SpaceService(SpaceRepository spaceRepository, UserRepository userRepository) {
        this.spaceRepository = spaceRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public SpaceResult createSpace(Space space) {
        spaceRepository.save(space);
        spaceRepository.saveUsersSpace(space.getAuthorId(), space.getId());
        return SpaceResult.SpaceCreated;
    }

    public Result addUser(String userName, int spaceId) {
        User user = userRepository.findByName(userName);
        if (user == null)
            return UserResult.UserNotFound;
        spaceRepository.saveUsersSpace(user.getId(), spaceId);
        return SpaceResult.UserAdded;
    }

    public boolean checkUserExistsInSpace(int userId, int spaceId) {
        return !spaceRepository.findUserInSpace(userId, spaceId).isEmpty();
    }
}
