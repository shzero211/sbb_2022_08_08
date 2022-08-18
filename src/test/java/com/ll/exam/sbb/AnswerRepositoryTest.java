package com.ll.exam.sbb;

import com.ll.exam.sbb.answer.Answer;
import com.ll.exam.sbb.answer.AnswerRepository;
import com.ll.exam.sbb.Question.Question;
import com.ll.exam.sbb.Question.QuestionRepository;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
        answerRepository.deleteAll();
      answerRepository.truncateTable();
   }
   private void createSampleData(){
    QuestionRepositoryTest.createSampleData(questionRepository);
    Question q1=questionRepository.findById(1).get();
       Answer a1=new Answer("content1",LocalDateTime.now());
       Answer a2=new Answer("content2",LocalDateTime.now());
       a1.addAnswer(q1);
       a2.addAnswer(q1);
       answerRepository.save(a1);
       answerRepository.save(a2);
   }

   @Test
   @Transactional
    void 저장(){
    Answer a1=new Answer("content3",LocalDateTime.now(),questionRepository.findById(1).get());
    answerRepository.save(a1);
    assertThat(a1.getId()).isEqualTo(3);
   }
   @Test
   @Transactional
    void 조회(){
       Answer a=answerRepository.findById(1).get();
       assertThat(a.getContent()).isEqualTo("content1");
   }
   @Test
   @Transactional
    void 관련된_question_조회(){
       Answer a=answerRepository.findById(1).get();
       Question q=a.getQuestion();
       assertThat(q.getId()).isEqualTo(1);
   }
    @Test
    @Transactional
    @Rollback(value = false)
    public void question_으로부터_관련된_답변들_조회(){
        Question q=questionRepository.findById(1).get();
        List<Answer> answerList=q.getAnswerList();
        assertThat(answerList.size()).isEqualTo(2);
    }
}
