package com.ll.exam.sbb.Question;

import com.ll.exam.sbb.user.SiteUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public void create(Question q) {
        questionRepository.save(q);
    }
    public Page<Question> getList(int page,String kw,String sortCode){
        List<Sort.Order> sorts=new ArrayList<>();
        Pageable pageable;
        if(sortCode.equals("NEWEST")){
            sorts.add(Sort.Order.desc("createDate"));
           pageable = PageRequest.of(page,10,Sort.by(sorts));
        }else if(sortCode.equals("OLDEST")){
            sorts.add(Sort.Order.asc("createDate"));
            pageable = PageRequest.of(page,10,Sort.by(sorts));
        }else {
            pageable=PageRequest.of(page,10);
        }
        return questionRepository.findAll(pageable,kw);
    }
    public void modify(Question question,String subject,String content){
        question.setSubject(subject);
        question.setContent(content);
        question.setModifyDate(LocalDateTime.now());
        questionRepository.save(question);
    }

    public void delete(Question question) {
        questionRepository.delete(question);
    }

    public void vote(Question question, SiteUser siteUser) {
        question.getVoter().add(siteUser);
        questionRepository.save(question);
    }
}
