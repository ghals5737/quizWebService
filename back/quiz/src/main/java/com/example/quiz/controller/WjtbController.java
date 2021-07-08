package com.example.quiz.controller;

import com.example.quiz.dto.Encyc;
import com.example.quiz.dto.EncycDetail;
import com.example.quiz.service.WjtbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/wjtb")
@RestController
public class WjtbController {
    @Resource(name="wjtbService")
    private WjtbService wjtbService;

    @GetMapping("/search")
    public List<Encyc> search(String keyWord) throws UnsupportedEncodingException {
        return wjtbService.search(keyWord);
    }

    @GetMapping("/searchdetail")
    public EncycDetail searchDetail(String dictSeq) throws UnsupportedEncodingException {
        return wjtbService.searchDetail(dictSeq);
    }
}
