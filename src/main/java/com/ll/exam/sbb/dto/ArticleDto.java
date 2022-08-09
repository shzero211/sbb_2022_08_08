package com.ll.exam.sbb.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class ArticleDto {
    private static int lastId=1;
    private int id;
    private String title;
    private String body;
    public ArticleDto(String title,String body){
        this(lastId++,title,body);
    }
}
