package com.ll.exam.sbb;

import com.ll.exam.sbb.domain.Answer;
import com.ll.exam.sbb.domain.AnswerRepository;
import com.ll.exam.sbb.domain.Question;
import com.ll.exam.sbb.domain.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class QuestionRepositoryTest {
    @Autowired
    private QuestionRepository questionRepository;


    @Test
    @Order(1)
    public void save(){
        Question q1=new Question(1,"subject1","content1", LocalDateTime.now(),new ArrayList<>());
        questionRepository.save(q1);
        Question q2=new Question(2,"subject2","content2", LocalDateTime.now(),new ArrayList<>());
        questionRepository.save(q2);
        assertEquals(2,questionRepository.findAll().size());
    }
    @Test
    public void findById(){
        Optional<Question> oq=this.questionRepository.findById(1);
        if(oq.isPresent()){
            Question q=oq.get();
            assertEquals("content1",q.getContent());
        }
    }
    @Test
    public void findBySubject(){
        Question q=questionRepository.findBySubject("subject1");
        assertEquals(1,q.getId());
    }
    @Test
    public void findBySubjectAndContent(){
        Question q=questionRepository.findBySubjectAndContent("subject2","content2");
        assertEquals(2,q.getId());
    }
    @Test
    public void findBySubjectLike(){
        List<Question> questions= questionRepository.findBySubjectLike("%1");
        assertEquals(1,questions.size());
    }

    @Test
    public void update(){
        Optional<Question> oq=questionRepository.findById(1);
        if(oq.isPresent()){
            Question q=oq.get();
            q.setSubject("subjectUp");
            questionRepository.save(q);
        }
        assertEquals("subjectUp",questionRepository.findById(1).get().getSubject());
        Question q=oq.get();
        q.setSubject("subject1");
        questionRepository.save(q);
        assertEquals("subject1",questionRepository.findById(1).get().getSubject());
    }
    /*@Test
    public void delete(){
        Optional<Question> oq=questionRepository.findById(1);
        Question q=oq.get();
        questionRepository.delete(q);
        assertEquals(1,questionRepository.findAll().size());
        questionRepository.save(q);
        assertEquals(2,questionRepository.findAll().size());
    }*/
}
