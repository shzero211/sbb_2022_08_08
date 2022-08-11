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
       QuestionRepositoryTest.clearData(questionRepository);

      answerRepository.truncateTable();
   }
   private void createSampleData(){
    QuestionRepositoryTest.createSampleData(questionRepository);
       Answer a1=new Answer("content1",LocalDateTime.now(),questionRepository.findById(1).get());
       Answer a2=new Answer("content2",LocalDateTime.now(),questionRepository.findById(2).get());
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
