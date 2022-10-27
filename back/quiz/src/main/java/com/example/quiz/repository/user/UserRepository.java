package com.example.quiz.repository.user;

import com.example.quiz.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByUserNo(Long user);
    User findUserByUserId(String userId);
    Optional<User> findByUserId(String userId);
    boolean existsByUserId(String userId);
}
