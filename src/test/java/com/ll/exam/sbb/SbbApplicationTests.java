package com.ll.exam.sbb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;

@SpringBootTest
class SbbApplicationTests {
	@Autowired
	private QuestionRepository questionRepository;
	@Test
	public void testJpa(){
		Question q1=new Question("sbb가 무엇인가요?","sbb가좋나여?", LocalDateTime.now();
		questionRepository.save(q1);
		Question q2=new Question("sbb가 무엇인가요?2","sbb가좋나여?2", LocalDateTime.now());
		questionRepository.save(q2);
	}

}
