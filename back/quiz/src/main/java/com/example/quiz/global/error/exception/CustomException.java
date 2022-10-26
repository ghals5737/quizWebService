package com.example.quiz.global.error.exception;

import com.example.quiz.global.error.code.ErroCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException{
    private final ErroCode erroCode;
}
