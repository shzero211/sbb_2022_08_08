package com.ll.exam.sbb;

import com.ll.exam.sbb.domain.Answer;
import com.ll.exam.sbb.domain.AnswerRepository;
import com.ll.exam.sbb.domain.Question;
import com.ll.exam.sbb.domain.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AnswerRepositoryTest {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Test
    public void save(){
        Optional<Question> oq=questionRepository.findById(1);
        Question q=oq.get();

        Answer a=new Answer(1,"content1", LocalDateTime.now(),q);
        q.getAnswerList().add(a);
        answerRepository.save(a);
        assertEquals("content1",answerRepository.findById(1).get().getContent());
    }

    @Test
    public void answer_에_연결된_question_Subject_찾기(){
        Optional<Answer> oa=answerRepository.findById(1);
        Answer a=oa.get();
        assertEquals("subject1",a.getQuestion().getSubject());
    }
    @Test
    @Transactional
    public void Quest_에연결된_Answer_content찾기(){
        Optional<Question>oq=questionRepository.findById(1);
        Question q=oq.get();
      assertEquals("content1",  q.getAnswerList().get(0).getContent());
    }
}
