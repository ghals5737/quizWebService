package com.example.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Enter {
    private String userId;
    private Long userNo;
    private Long roomNo;
    private Boolean isOwner;
}
