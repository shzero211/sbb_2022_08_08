package com.ll.exam.sbb.controller;

import com.ll.exam.sbb.domain.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonController {
    List<Person> persons =new ArrayList<>();

    @GetMapping("/addPerson")
    @ResponseBody
    public String addPerson(@ModelAttribute  Person p){
        Person person=new Person(p.getId(),p.getAge(),p.getName());
        persons.add(person);
        return "%d번 사람이 저장되었습니다".formatted(person.getId());
    }

    @GetMapping("/person/{id}")
    @ResponseBody
    public Person getPerson(@PathVariable  int id){
        Person person=persons.stream().filter(a->a.getId()==id).findFirst().orElse(null);
        if(person==null){
            return null;
        }
        return person;
    }


}
