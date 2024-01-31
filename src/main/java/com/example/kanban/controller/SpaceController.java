package com.example.kanban.controller;

import com.example.kanban.entity.Space;
import com.example.kanban.entity.User;
import com.example.kanban.repository.SpaceRepository;
import com.example.kanban.repository.UserRepository;
import com.example.kanban.result.Result;
import com.example.kanban.result.SpaceResult;
import com.example.kanban.result.UserResult;
import com.example.kanban.service.SpaceService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/spaces")
public class SpaceController {
    private final SpaceRepository spaceRepository;
    private final UserRepository userRepository;
    private final SpaceService spaceService;

    public SpaceController(SpaceRepository spaceRepository, UserRepository userRepository, SpaceService spaceService) {
        this.spaceRepository = spaceRepository;
        this.userRepository = userRepository;
        this.spaceService = spaceService;
    }

    @GetMapping()
    public List<Space> getAllSpacesOfUser(@AuthenticationPrincipal User user) {
        return spaceRepository.findAllByUserId(user.getId());
    }

    @GetMapping("/{spaceId}/users")
    public List<User> getAllUsersOfSpace(@AuthenticationPrincipal User user, @PathVariable("spaceId") int spaceId) {
        List<User> allUsersBySpaceId = userRepository.findAllUsersBySpaceId(spaceId);
        return allUsersBySpaceId;
    }

    @PostMapping("/{spaceId}/users")
    public String addUserToSpace(@AuthenticationPrincipal User user, @PathVariable("spaceId") int spaceId, @RequestParam("userName") String userName, HttpServletResponse httpServletResponse) {
        Result result = spaceService.addUser(userName, spaceId);
        if (result == UserResult.UserNotFound)
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return result.getMessage();
    }
    @DeleteMapping("/{spaceId}/users")
    public String deleteUserFromSpace(@AuthenticationPrincipal User user, @PathVariable("spaceId") int spaceId, @RequestParam("userId") int userId, HttpServletResponse httpServletResponse) {
        return spaceService.deleteSpace(userId, spaceId).getMessage();
    }

    @DeleteMapping()
    public String deleteUserFromSpace(@AuthenticationPrincipal User user, @RequestParam("spaceId") int spaceId) {
        return spaceService.deleteSpace(user.getId(), spaceId).getMessage();
    }

    @PostMapping
    public String createSpace(@AuthenticationPrincipal User user, @RequestBody Space space, HttpServletResponse httpServletResponse) {
        space.setAuthorId(user.getId());
        SpaceResult spaceResult = spaceService.createSpace(space);
        if (spaceResult != SpaceResult.SpaceCreated) {
            httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return spaceResult.getMessage();
    }
}
