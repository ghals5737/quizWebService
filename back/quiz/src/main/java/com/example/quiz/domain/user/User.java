package com.example.quiz.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userNo;
    private String userId;
    private String userPw;
    @CreationTimestamp
    private LocalDateTime regTime;
    private String authorityName;

    static public User toUser(String userId,String UserPw){
        return User.builder()
                .userId(userId)
                .userPw(UserPw)
                .regTime(LocalDateTime.now())
                .build();
    }
}
