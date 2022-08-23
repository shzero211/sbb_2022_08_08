package com.ll.exam.sbb.answer;

import com.ll.exam.sbb.Question.DataNotFoundException;
import com.ll.exam.sbb.Question.Question;
import com.ll.exam.sbb.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    public void create(Question question, String content, SiteUser siteUser) {
        Answer answer=new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setAuthor(siteUser);
        answer.addAnswer(question);
        answerRepository.save(answer);
    }
    public Answer getAnswer(int id){
        return answerRepository.findById(id).orElseThrow(()->new DataNotFoundException("no %d answer not found".formatted(id)));
    }
    public void modify(Answer answer, String content) {
        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now());
        this.answerRepository.save(answer);
    }
}
