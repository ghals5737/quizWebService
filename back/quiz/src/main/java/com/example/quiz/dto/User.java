package com.example.quiz.dto;

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
    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "userNo", referencedColumnName = "userNo")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;
}
