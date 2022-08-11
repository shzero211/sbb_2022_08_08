package com.ll.exam.sbb.Question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Integer> {
    Question findBySubject(String subject);
    Question findBySubjectAndContent(String subject,String content);
    List<Question> findBySubjectLike(String subject);

    @Transactional
    @Modifying
    @Query(
            value = "truncate question",
            nativeQuery = true
    )
    void truncateMyTable();

    @Transactional
    @Modifying
    @Query(
            value = "SET FOREIGN_KEY_CHECKS = 0",
            nativeQuery = true
    )
    void disableForeignKeyCheck();

    @Transactional
    @Modifying
    @Query(
            value = "SET FOREIGN_KEY_CHECKS = 1",
            nativeQuery = true
    )
    void enableForeignKeyCheck();
}
