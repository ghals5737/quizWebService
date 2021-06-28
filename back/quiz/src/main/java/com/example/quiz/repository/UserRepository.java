package com.example.quiz.repository;

import com.example.quiz.dto.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    User findUserByUserNo(Long user);
    User findUserByUserId(String userId);
    boolean existsByUserId(String userId);
}
