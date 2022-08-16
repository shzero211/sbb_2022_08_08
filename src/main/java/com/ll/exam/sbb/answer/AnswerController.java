package com.ll.exam.sbb.answer;

import com.ll.exam.sbb.Question.Question;
import com.ll.exam.sbb.Question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
    private final QuestionService questionService;
    private final AnswerService answerService;
    @PostMapping("/create/{id}")
    public String detail(Model model, @PathVariable int id, String content){
        Question question=questionService.findById(id);
        answerService.create(question,content);
        return "redirect:/question/detail/%d".formatted(id);
    }
}
