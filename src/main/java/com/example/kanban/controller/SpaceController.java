package com.example.kanban.controller;

import com.example.kanban.entity.Space;
import com.example.kanban.entity.User;
import com.example.kanban.repository.SpaceRepository;
import com.example.kanban.result.SpaceResult;
import com.example.kanban.service.SpaceService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spaces")
public class SpaceController {
    private final SpaceRepository spaceRepository;
    private final SpaceService spaceService;

    public SpaceController(SpaceRepository spaceRepository, SpaceService spaceService) {
        this.spaceRepository = spaceRepository;
        this.spaceService = spaceService;
    }

    @GetMapping()
    public List<Space> getAllSpacesOfUser(@AuthenticationPrincipal User user) {
        return spaceRepository.findAllByUserId(user.getId());
    }

    @PostMapping
    public String createSpace(@AuthenticationPrincipal User user, @RequestBody Space space, HttpServletResponse httpServletResponse) {
        space.setAuthorId(user.getId());
        SpaceResult spaceResult = spaceService.createSpace(space);
        if (spaceResult!= SpaceResult.SpaceCreated) {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return spaceResult.getMessage();
        }
        return String.valueOf(spaceResult.getSpaceId());
    }
}
