package com.usermanagement.main.repository;

import com.usermanagement.main.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByUsername(String username);

	@SuppressWarnings("unchecked")
	UserEntity save(UserEntity user);
}
