package com.ll.exam.sbb;

import com.ll.exam.sbb.Question.Question;
import com.ll.exam.sbb.Question.QuestionRepository;
import static org.assertj.core.api.Assertions.assertThat;

import com.ll.exam.sbb.answer.Answer;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class QuestionRepositoryTest {
    @Autowired
    private QuestionRepository questionRepository;

    private static int lastSampleDataId;
   @BeforeEach
    void beforeEach(){
       clearData();
       createSampleData();
   }
  public static void clearData(QuestionRepository questionRepository){
        questionRepository.deleteAll();
       questionRepository.truncateTable();
   }
  public static int createSampleData(QuestionRepository questionRepository){
      Question q1=null;
       for(int i=1;i<=40;i++){
        q1=new Question("제목%s".formatted(i),"내용%s".formatted(i),LocalDateTime.now());
           questionRepository.save(q1);
       }
    return q1.getId();
   }
   private void createSampleData(){
       lastSampleDataId=createSampleData(questionRepository);
   }
   private void clearData(){
       clearData(questionRepository);
   }
   @Test
    void 저장(){
       Question q1=new Question("subject3","content3", LocalDateTime.now());
       Question q2=new Question("subject4","content4", LocalDateTime.now());
       questionRepository.save(q1);
       questionRepository.save(q2);
       assertThat(q1.getId()).isEqualTo(lastSampleDataId+1);
       assertThat(q2.getId()).isEqualTo(lastSampleDataId+2);
   }
   @Test
    void 삭제(){
       assertThat(questionRepository.count()).isEqualTo(lastSampleDataId);
       Question q=questionRepository.findById(1).get();
       questionRepository.delete(q);
       assertThat(questionRepository.count()).isEqualTo(lastSampleDataId-1);
   }
   @Test
    void 수정(){
       Question q=questionRepository.findById(1).get();
       q.setSubject("수정된 제목");
       questionRepository.save(q);
       q=questionRepository.findById(1).get();
        assertThat(q.getSubject()).isEqualTo("수정된 제목");
   }
   @Test
    void findAll(){
       List<Question> all=questionRepository.findAll();
       assertThat(all.size()).isEqualTo(lastSampleDataId);
   }
   @Test
    void findBySubject(){
       Question q=questionRepository.findBySubject("제목1");
       assertThat(q.getId()).isEqualTo(1);
   }
   @Test
    void findBySubjectAndContent(){
       Question q=questionRepository.findBySubjectAndContent("제목2","내용2");
       assertThat(q.getId()).isEqualTo(2);
   }
   @Test
    void findBySubjectLike(){
       List<Question> qList=questionRepository.findBySubjectLike("제%");
       assertThat(qList.size()).isEqualTo(40);
   }


}
