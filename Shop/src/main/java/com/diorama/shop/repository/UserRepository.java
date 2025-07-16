package com.diorama.shop.repository;

import com.diorama.shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByVerificationToken(String verificationToken);
    boolean existsByUsername(String username);
    boolean existsByEmailAndIsActiveTrue(String email);
    boolean existsByPhoneAndIsActiveTrue(String phone);
}