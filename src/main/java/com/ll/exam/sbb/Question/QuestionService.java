package com.ll.exam.sbb.Question;

import com.ll.exam.sbb.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    public Question findById(int id){
        Optional<Question> question=questionRepository.findById(id);
    if(question.isPresent()){
        return question.get();
    }else{
        throw new DataNotFoundException("question data not found");
    }
    }
    public List<Question> findAll() {
        return questionRepository.findAll();
    }
}
