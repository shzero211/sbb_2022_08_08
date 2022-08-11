package com.ll.exam.sbb;

import com.ll.exam.sbb.answer.Answer;
import com.ll.exam.sbb.answer.AnswerRepository;
import com.ll.exam.sbb.Question.Question;
import com.ll.exam.sbb.Question.QuestionRepository;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
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
    private static int lastSampleDataId;
   @Autowired
    private QuestionRepository questionRepository;

   @Autowired
    private AnswerRepository answerRepository;

   static int lastIdx;

   @BeforeEach
   private void beforeEach(){
       clearData();
       createSampleData();
   }
   private void clearData(){
       questionRepository.disableForeignKeyCheck();
       questionRepository.truncateMyTable();
       answerRepository.truncateMyTable();
       questionRepository.enableForeignKeyCheck();
   }
   private void createSampleData(){
    Question q1=new Question("subject1","content1",LocalDateTime.now());
       Question q2=new Question("subject2","content2",LocalDateTime.now());
       questionRepository.save(q1);
       questionRepository.save(q2);

       Answer a1=new Answer("content1",LocalDateTime.now(),q1);
       Answer a2=new Answer("content2",LocalDateTime.now(),q2);
       answerRepository.save(a1);
       answerRepository.save(a2);
       lastIdx=a2.getId();
   }

   @Test
    void 저장(){
    Answer a1=new Answer("content3",LocalDateTime.now(),questionRepository.findById(1).get());
    answerRepository.save(a1);
    assertThat(a1.getId()).isEqualTo(lastIdx+1);
   }
}
