package com.example.kanban.service;

import com.example.kanban.entity.Space;
import com.example.kanban.repository.SpaceRepository;
import com.example.kanban.result.SpaceResult;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpaceService {
    private final SpaceRepository spaceRepository;

    @Autowired
    public SpaceService(SpaceRepository spaceRepository) {
        this.spaceRepository = spaceRepository;
    }

    @Transactional
    public SpaceResult createSpace(Space space) {
        spaceRepository.save(space);
        spaceRepository.saveUsersSpace(space.getUserId(), space.getId());
        SpaceResult spaceCreated = SpaceResult.SpaceCreated;
        spaceCreated.setSpaceId(space.getId());
        return spaceCreated;
    }
}
