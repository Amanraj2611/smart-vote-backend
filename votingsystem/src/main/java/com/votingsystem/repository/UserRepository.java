package com.votingsystem.repository;

import com.votingsystem.entity.User;
import com.votingsystem.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    // ✅ NEW (count voters)
    long countByRole(Role role);
}