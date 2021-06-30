package com.example.quiz.repository;

import com.example.quiz.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByUserNo(Long user);
    User findUserByUserId(String userId);
    Optional<User> findByUserId(String userId);
    boolean existsByUserId(String userId);
}
