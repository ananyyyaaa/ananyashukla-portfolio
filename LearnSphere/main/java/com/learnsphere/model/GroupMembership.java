package com.learnsphere.model;

import jakarta.persistence.*;

@Entity
@Table(name = "group_memberships")
public class GroupMembership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long groupId;

    public GroupMembership() {
    }

    public GroupMembership(Long userId, Long groupId) {
        this.userId = userId;
        this.groupId = groupId;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
