package com.ll.exam.sbb.user;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class UserController {
    private final UserService userService;

    @GetMapping("/signup")
    public String signup(UserCreateForm userCreateForm){
        return "signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid  UserCreateForm userCreateForm, BindingResult bindingResult, Model model){
       if(bindingResult.hasErrors()){
           return "signup_form";
       }

       if(!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
           bindingResult.rejectValue("password2", "passwordInCorrect", "2개의 패스워드가 일치하지 않습니다.");
           return "signup_form";
       }

      try{
          userService.create(userCreateForm.getUsername(), userCreateForm.getEmail(), userCreateForm.getPassword1());

      }catch (DataIntegrityViolationException e){
          e.printStackTrace();
          model.addAttribute("dataError","이미 등록된 사용자 ID 입니다.");
          return "signup_form";
      }
       return "redirect:/question/list";
    }

}