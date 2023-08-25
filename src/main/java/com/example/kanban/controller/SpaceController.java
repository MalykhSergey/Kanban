package com.example.kanban.controller;

import com.example.kanban.entity.Space;
import com.example.kanban.entity.User;
import com.example.kanban.repository.SpaceRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/spaces")
public class SpaceController {
    private final SpaceRepository spaceRepository;

    public SpaceController(SpaceRepository spaceRepository) {
        this.spaceRepository = spaceRepository;
    }

    @GetMapping()
    public List<Space> getAllSpacesOfUser(@AuthenticationPrincipal User user){
        return spaceRepository.findAllByUserId(user.getId());
    }
}
