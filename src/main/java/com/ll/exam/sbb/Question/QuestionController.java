package com.ll.exam.sbb.Question;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {
    private final QuestionService questionService;

    @RequestMapping("/list")
    public String showList(Model model){
    List<Question> questionList=questionService.findAll();
    model.addAttribute("questionList",questionList);
    return "question_list";
    }
    @RequestMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id){
        Question question=questionService.findById(id);
        model.addAttribute("question",question);
        return "question_detail";
    }
}
