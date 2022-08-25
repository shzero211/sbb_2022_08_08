package com.ll.exam.sbb.Question;

import com.ll.exam.sbb.answer.AnswerForm;
import com.ll.exam.sbb.user.SiteUser;
import com.ll.exam.sbb.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public String showList(Model model,@RequestParam(value = "page",defaultValue = "0")int page,
                           @RequestParam(value = "kw",defaultValue = "") String kw,
                           @RequestParam(value = "sortCode",defaultValue = "") String sortCode){
    Page<Question> paging=questionService.getList(page,kw,sortCode);
    model.addAttribute("paging",paging);
    model.addAttribute("kw",kw);
    model.addAttribute("sortCode",sortCode);
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

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String questionModify(QuestionForm questionForm,@PathVariable("id") Integer id,Principal principal ){
        Question q=questionService.findById(id);
        if(!q.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"수정 권한이 없습니다");
        }
        questionForm.setSubject(q.getSubject());
        questionForm.setContent(q.getContent());
        return "question_form";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String questionModify(@Valid QuestionForm questionForm,BindingResult bindingResult,Principal principal,@PathVariable("id") Integer id){
        if(bindingResult.hasErrors()){
            return "question_form";
        }
        Question question=questionService.findById(id);
        if (question == null) {
            throw new DataNotFoundException("%d번 질문은 존재하지 않습니다.");
        }
        if(!question.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"수정권한이 없습니다.");
        }
        questionService.modify(question,questionForm.getSubject(),questionForm.getContent());
        return "redirect:/question/detail/%s".formatted(id);
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String questionDelete(Principal principal, @PathVariable("id") Integer id) {
        Question question = questionService.findById(id);

        if (question == null) {
            throw new DataNotFoundException("%d번 질문은 존재하지 않습니다.");
        }

        if (!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }

        questionService.delete(question);

        return "redirect:/";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/{id}")
    public String questionVote(Principal principal,@PathVariable int id){
       Question question=questionService.findById(id);
       SiteUser siteUser=userService.getUser(principal.getName());
       questionService.vote(question,siteUser);
        return "redirect:/question/detail/%s".formatted(id);
    }
}
