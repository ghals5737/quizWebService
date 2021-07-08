package com.example.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Encyc {
    private String dictSeq;
    private String orgHeadwd;
    private String headwd;
    private String url;
    private String des;
}
