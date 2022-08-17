package com.ll.exam.sbb.Question;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm){
        return "question_form";
    }
    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "question_form";
        }
        Question question=questionForm.create();
        questionService.save(question);
        return "redirect:/question/list";
    }
}
