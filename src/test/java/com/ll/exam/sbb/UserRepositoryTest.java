package com.ll.exam.sbb;

import com.ll.exam.sbb.user.UserRepository;
import com.ll.exam.sbb.user.UserService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @AfterAll
    public void afterAll(){
        userRepository.deleteAll();
    }
    @Test
    public void save(){
        userService.create("user","user@naver,com","pass");
        Assertions.assertEquals("user",userRepository.findById(1L).get().getUsername());
    }
}
