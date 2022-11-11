package com.example.quiz.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Builder
@Getter
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

    public void updateUserId(String userId){
        this.userId=userId;
    }
}
