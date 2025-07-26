package com.learnsphere.repository;

import com.learnsphere.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findByCreatedByUserId(Long userId);
}
