package com.ll.exam.sbb.Question;

import com.ll.exam.sbb.answer.AnswerForm;
import com.ll.exam.sbb.user.SiteUser;
import com.ll.exam.sbb.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {
    private final QuestionService questionService;

    private final UserService userService;
    @RequestMapping("/list")
    public String showList(Model model,@RequestParam(value = "page",defaultValue = "0")int page){
    Page<Question> paging=questionService.getList(page);
    model.addAttribute("paging",paging);
    return "question_list";
    }
    @RequestMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm){
        Question question=questionService.findById(id);
        model.addAttribute("question",question);
        return "question_detail";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm){
        return "question_form";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal){

        if(bindingResult.hasErrors()){
            return "question_form";
        }
        SiteUser siteUser=userService.getUser(principal.getName());
        Question question=questionForm.create(siteUser);
        questionService.create(question);
        return "redirect:/question/list";
    }
}
