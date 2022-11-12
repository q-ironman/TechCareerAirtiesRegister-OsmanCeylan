package com.example.osmanceylan.repository;

import com.example.osmanceylan.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
    // delivered query
    Optional<User> findByUsername(String username);
}
