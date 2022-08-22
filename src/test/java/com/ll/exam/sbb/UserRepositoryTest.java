package com.ll.exam.sbb;

import com.ll.exam.sbb.Question.QuestionRepository;
import com.ll.exam.sbb.answer.AnswerRepository;
import com.ll.exam.sbb.user.UserRepository;
import com.ll.exam.sbb.user.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryTest {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void beforeEach(){
        clearData();
        createData();
    }
    public static void clearData(UserRepository userRepository){
        userRepository.deleteAll();
        userRepository.truncate();
    }
    public void clearData(){
        AnswerRepositoryTest.clearData(answerRepository);
        QuestionRepositoryTest.clearData(questionRepository);
        clearData(userRepository);
    }

    public static void createData(UserService userService){
        userService.create("user1","user1@naver.com","user1");
        userService.create("user2","user2@naver.com","user2");
    }

    public void createData(){
        createData(userService);
    }
    @Test
    public void save(){
        Assertions.assertEquals("user1",userRepository.findById(1L).get().getUsername());
    }
}
