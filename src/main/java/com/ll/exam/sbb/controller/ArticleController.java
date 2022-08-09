package com.ll.exam.sbb.controller;

import com.ll.exam.sbb.dto.ArticleDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class ArticleController {
    ArrayList<ArticleDto> articleDtos=new ArrayList<>();
    @GetMapping("/addArticle")
    @ResponseBody
    public String addArticle(@RequestParam(defaultValue = "") String title, @RequestParam(defaultValue = "") String body){

        ArticleDto articleDto=new ArticleDto(title,body);
        articleDtos.add(articleDto);
        return articleDto.getId()+"번 글이 등록되었습니다.";
    }
    @GetMapping("/article/{id}")
    @ResponseBody
    public ArticleDto getArticle(@PathVariable int id){
        ArticleDto articleDto= articleDtos
                .stream()
                .filter(a->a.getId()==id)
                .findFirst()
                .get();
        return articleDto;
    }
}
