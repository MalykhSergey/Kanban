package com.example.kanban.service;

import com.example.kanban.entity.Space;
import com.example.kanban.repository.SpaceRepository;
import com.example.kanban.result.Result;
import com.example.kanban.result.SpaceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpaceService {
    private final SpaceRepository spaceRepository;

    @Autowired
    public SpaceService(SpaceRepository spaceRepository) {
        this.spaceRepository = spaceRepository;
    }

    public Result createSpace(Space space) {
        spaceRepository.save(space);
        return SpaceResult.SpaceCreated;
    }
}
