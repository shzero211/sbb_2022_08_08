package com.ll.exam.sbb.answer;

import com.ll.exam.sbb.Question.Question;
import com.ll.exam.sbb.Question.QuestionService;
import com.ll.exam.sbb.user.SiteUser;
import com.ll.exam.sbb.user.UserRepository;
import com.ll.exam.sbb.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final UserService userService;
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable int id, @Valid AnswerForm answerForm, BindingResult bindingResult
    , Principal principal){
        Question question=questionService.findById(id);
        SiteUser siteUser =userService.getUser(principal.getName());
        if(bindingResult.hasErrors()){
            model.addAttribute("question",question);
            return "question_detail";
        }
        answerService.create(question,answerForm.getContent(),siteUser);
        return "redirect:/question/detail/%d".formatted(id);
    }
}
