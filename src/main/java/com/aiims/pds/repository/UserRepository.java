package com.aiims.pds.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aiims.pds.modals.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByContactNo(String username);

	Optional<User> getRoleByContactNo(String username);

}
