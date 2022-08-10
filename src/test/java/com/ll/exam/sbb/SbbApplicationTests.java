package com.ll.exam.sbb;

import com.ll.exam.sbb.domain.Answer;
import com.ll.exam.sbb.domain.AnswerRepository;
import com.ll.exam.sbb.domain.Question;
import com.ll.exam.sbb.domain.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SbbApplicationTests {
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private AnswerRepository answerRepository;

	@Test
	public void save(){
		Question q1=new Question(1,"subject1","content1", LocalDateTime.now(),new ArrayList<>());
		questionRepository.save(q1);
		Question q2=new Question(2,"subject2","content2", LocalDateTime.now(),new ArrayList<>());
		questionRepository.save(q2);
		assertEquals(2,questionRepository.findAll().size());

		Answer a1=new Answer(1,"content1", LocalDateTime.now(),q1);
		answerRepository.save(a1);
		Answer a2=new Answer(2,"content2", LocalDateTime.now(),q1);
		answerRepository.save(a2);
		assertEquals(2,answerRepository.findAll().size());
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

}
