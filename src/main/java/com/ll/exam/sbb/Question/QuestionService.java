package com.ll.exam.sbb.Question;

import com.ll.exam.sbb.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    public Question findById(int id){
    return questionRepository.findById(id).orElseThrow(()->new DataNotFoundException("no %d question not found".formatted(id)));
    }
    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    public void save(Question q) {
        questionRepository.save(q);
    }
    public Page<Question> getList(int page){
        Pageable pageable = PageRequest.of(page,10);
        return questionRepository.findAll(pageable);
    }
}
