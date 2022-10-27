package com.example.quiz.repository.user;

import com.example.quiz.domain.user.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.example.quiz.domain.user.QUser.user;


@RequiredArgsConstructor
@Repository
public class UserQuerydslRepository {
    private final JPAQueryFactory queryFactory;


    public Optional<User> find(String userId){
        return Optional.ofNullable(queryFactory.select(user)
                .from(user)
                .where(
                        user.userId.eq(userId)
                )
                .fetchOne());
    }
}
