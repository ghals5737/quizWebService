package com.example.quiz.service;

import com.example.quiz.dto.Encyc;
import com.example.quiz.dto.EncycDetail;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface WjtbService {
    List<Encyc> search(String keyWord) throws UnsupportedEncodingException;
    EncycDetail searchDetail(String dictSeq) throws UnsupportedEncodingException;
}
