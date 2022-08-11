package com.ll.exam.sbb.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @RequestMapping("/list")
    @ResponseBody
    public List<Question> showList(){
    List<Question> questionList=questionService.findAll();
    return questionList;
    }
}
