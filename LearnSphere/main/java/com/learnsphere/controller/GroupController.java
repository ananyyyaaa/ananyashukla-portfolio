package com.learnsphere.controller;

import com.learnsphere.model.Group;
import com.learnsphere.model.User;
import com.learnsphere.service.GroupService;
import com.learnsphere.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/groups")
@CrossOrigin(origins = "http://localhost:5173")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createGroup(@RequestBody Map<String, Object> groupData) {
        String name = (String) groupData.get("name");
        String description = (String) groupData.get("description");
        Number createdByUserIdNum = (Number) groupData.get("createdByUserId");
        if (name == null || createdByUserIdNum == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Missing required fields"));
        }
        Long createdByUserId = createdByUserIdNum.longValue();
        Group group = new Group(name, description, createdByUserId);
        Group savedGroup = groupService.createGroup(group);
        return ResponseEntity.ok(savedGroup);
    }

    @PostMapping("/{groupId}/join")
    public ResponseEntity<?> joinGroup(@PathVariable Long groupId, @RequestBody Map<String, Object> requestData) {
        Number userIdNum = (Number) requestData.get("userId");
        if (userIdNum == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Missing userId"));
        }
        Long userId = userIdNum.longValue();
        groupService.joinGroup(userId, groupId);
        return ResponseEntity.ok(Map.of("message", "User joined the group"));
    }

    @GetMapping("/my/{userId}")
    public ResponseEntity<?> getUserGroups(@PathVariable Long userId) {
        List<Group> groups = groupService.getGroupsByUserId(userId);
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/{groupId}/members")
    public ResponseEntity<?> getGroupMembers(@PathVariable Long groupId) {
        List<Long> userIds = groupService.getGroupMemberUserIds(groupId);
        List<User> users = userIds.stream()
                .map(userService::getUserById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
        return ResponseEntity.ok(users);
    }
}
