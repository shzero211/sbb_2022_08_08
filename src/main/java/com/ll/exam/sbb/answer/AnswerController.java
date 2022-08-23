package com.ll.exam.sbb.answer;

import com.ll.exam.sbb.Question.Question;
import com.ll.exam.sbb.Question.QuestionService;
import com.ll.exam.sbb.user.SiteUser;
import com.ll.exam.sbb.user.UserRepository;
import com.ll.exam.sbb.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

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
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public  String modifyAnswer (AnswerForm answerForm,@PathVariable("id") int id,Principal principal){
        Answer answer=answerService.getAnswer(id);
        if(!answer.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"수정권한이 없다.");
        }
        answerForm.setContent(answer.getContent());
        return "answer_form";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String modifyAnswer(@Valid AnswerForm answerForm,BindingResult bindingResult,@PathVariable int id,Principal principal){
        if(bindingResult.hasErrors()){
            return "answer_form";
        }
        Answer answer=answerService.getAnswer(id);
        if(!answer.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"수정권한이 없습니다.");
        }
        answerService.modify(answer,answerForm.getContent());
        return "redirect:/question/detail/%s".formatted(answer.getQuestion().getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String deleteAnswer(Principal principal,@PathVariable("id")int id){
        Answer answer=answerService.getAnswer(id);
        if(!answer.getAuthor().getUsername().equals(principal.getName())){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"삭제 권한이 업습니다.");
        }
        answerService.delete(answer);
        return "redirect:/question/detail/%s".formatted(answer.getQuestion().getId());
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/{id}")
    public  String voteAnswer(Principal principal,@PathVariable int id){
            Answer answer=answerService.getAnswer(id);
            SiteUser siteUser=userService.getUser(principal.getName());
            answerService.vote(answer,siteUser);
            return "redirect:/question/detail/%s".formatted(answer.getQuestion().getId());
    }
}
