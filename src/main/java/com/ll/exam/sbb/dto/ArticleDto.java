package com.ll.exam.sbb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ArticleDto {
    private static int lastId=1;
    private final int id;
    private final String title;
    private final String body;
    public ArticleDto(String title,String body){
        this(lastId++,title,body);
    }
}
