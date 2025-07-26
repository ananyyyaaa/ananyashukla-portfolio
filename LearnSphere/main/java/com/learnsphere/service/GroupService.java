package com.learnsphere.service;

import com.learnsphere.model.Group;
import com.learnsphere.model.GroupMembership;
import com.learnsphere.model.User;
import com.learnsphere.repository.GroupMembershipRepository;
import com.learnsphere.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupMembershipRepository groupMembershipRepository;

    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }

    public GroupMembership joinGroup(Long userId, Long groupId) {
        GroupMembership membership = new GroupMembership(userId, groupId);
        return groupMembershipRepository.save(membership);
    }

    public List<Group> getGroupsByUserId(Long userId) {
        List<GroupMembership> memberships = groupMembershipRepository.findByUserId(userId);
        List<Long> groupIds = memberships.stream().map(GroupMembership::getGroupId).collect(Collectors.toList());
        return groupRepository.findAllById(groupIds);
    }

    public List<Long> getGroupMemberUserIds(Long groupId) {
        List<GroupMembership> memberships = groupMembershipRepository.findByGroupId(groupId);
        return memberships.stream().map(GroupMembership::getUserId).collect(Collectors.toList());
    }

    public Optional<Group> getGroupById(Long groupId) {
        return groupRepository.findById(groupId);
    }
}
