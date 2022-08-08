package com.ll.exam.sbb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @RequestMapping("/sbb")
    @ResponseBody
    public String index(){
        return "<html><body><h1>이게나야!!!!~~~~</h1></body></html>";
    }

    @GetMapping("/page1")
    @ResponseBody
    public String shwPage1(){
        return """
                <form method="POST" action="/page2">
                    <input type="number" name="age" placeholder="나이"/>
                    <input type="submit" value="page2로 Post방식으로이동"/>
                </form>
                """;
    }
    @PostMapping("/page2")
    @ResponseBody
    public String showPage2Post(@RequestParam(defaultValue = "0")int age){
        return """
                <h1>입력된 나이: %d</h1>
                <h1>안녕하세요,POST 방식으로 오셨군요.</h1>
                
                """.formatted(age);
    }
    @GetMapping("/page2")
    @ResponseBody
    public String showPage2Get(@RequestParam(defaultValue = "0")int age){
        return """
                
                <h1>입력된 나이: %d</h1>
                <h1>안녕하세요,POST 방식으로 오셨군요.</h1>
                
                """.formatted(age);
    }
    @GetMapping("/plus")
    @ResponseBody
    public String plus(@RequestParam(defaultValue = "0")int a,@RequestParam(defaultValue = "0")int b){
        return a+b+"";
    }
    @GetMapping("/minus")
    @ResponseBody
    public String minus(@RequestParam(defaultValue = "0")int a,@RequestParam(defaultValue = "0")int b){
        return a-b+"";
    }
    static int a=0;
    @GetMapping("/increase")
    @ResponseBody
    public String increase(){
        return a+++"";

    }
    @GetMapping("/gugudan")
    @ResponseBody
    public String gugudan(int dan,int limit){
        String str="";
        for(int i=1;i<=limit;i++){
            str+= """
                    %d * %d = %d <br>
                    
                    """.formatted(dan,i,dan*i);
        }
        return str;
    }
    @GetMapping("/mbti")
    @ResponseBody
    public String mbti(String name){
        String str="";
        switch (name){
            case "홍길동":
                str="ESTJ";
                break;
            case "홍길순":
                str= "ESTJ";
                break;
            case "몰랑":
                str="ESTJ";
                break;
            case "김상훈":
                str= "ESTJ";
                break;
        }
        return str;
    }
}
