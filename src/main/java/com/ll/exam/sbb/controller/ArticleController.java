package com.ll.exam.sbb.controller;

import com.ll.exam.sbb.dto.ArticleDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

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
                .orElse(null);
        if(articleDto==null){
            return null;
        }
        return articleDto;
    }
    @GetMapping("/modifyArticle")
    @ResponseBody
    public String modifyArticle(@RequestParam  int id,@RequestParam String title,@RequestParam String body)  {
        ArticleDto articleDto = articleDtos
                .stream()
                .filter(a -> a.getId() == id) // 1번
                .findFirst()
                .orElse(null);

        if(articleDto==null){
            return "%d번 게시물은 존재하지 않습니다.".formatted(id);
        }
        articleDto.setTitle(title);
        articleDto.setBody(body);
        return "%d번 글이 수정되었습니다.".formatted(id);
    }
    @GetMapping("/deleteArticle/{id}")
    @ResponseBody
    public String deleteArticle(@PathVariable int id){
        ArticleDto articleDto=articleDtos.stream()
                .filter(a->a.getId()==id)
                .findFirst()
                .orElse(null);
        if(articleDto==null){
            return "%d번 게시물은 존재하지 않습니다.".formatted(id);
        }
        articleDtos.remove(articleDto);

        return "%d번 게시물을 삭제하였습니다.".formatted(articleDto.getId());
    }
}
