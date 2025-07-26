package com.usermanagement.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.usermanagement.main.entity.CartEntity;
@Repository
public interface CartRepo extends JpaRepository<CartEntity, Long> {
    List<CartEntity> findByUserId(Long userId);
}
