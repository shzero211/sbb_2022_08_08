package com.ll.exam.sbb;

import com.ll.exam.sbb.answer.Answer;
import com.ll.exam.sbb.answer.AnswerRepository;
import com.ll.exam.sbb.Question.Question;
import com.ll.exam.sbb.Question.QuestionRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AnswerRepositoryTest {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @BeforeAll
    public void init(){
        questionRepository.save(new Question(1,"subject1","content1",LocalDateTime.now(),new ArrayList<>()));
        Optional<Question> oq=questionRepository.findById(1);
        Question q=oq.get();

        Answer a=new Answer(1,"content1", LocalDateTime.now(),q);
        answerRepository.save(a);
    }
    @Test
    public void save(){
        assertEquals("content1",answerRepository.findById(1).get().getContent());
    }

    @Test
    @Transactional
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
