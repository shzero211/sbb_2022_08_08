package com.ll.exam.sbb.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.util.List;
@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    public Question findById(int id){
      return questionRepository.findById(id).get();
    }
    public List<Question> findAll() {
        return questionRepository.findAll();
    }
}
