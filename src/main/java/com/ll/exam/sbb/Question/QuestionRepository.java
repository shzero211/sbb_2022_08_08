package com.ll.exam.sbb.Question;

import com.ll.exam.sbb.base.RepositoryUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Integer>, RepositoryUtil {
    Question findBySubject(String subject);
    Question findBySubjectAndContent(String subject,String content);
    List<Question> findBySubjectLike(String subject);

    @Query(value = "SELECT q from Question q " +
            "LEFT JOIN q.author auth " +
            "LEFT JOIN q.answerList a " +
            "LEFT JOIN a.author auth2 " +
            "where q.subject like %:kw% " +
            "or q.content like %:kw% " +
            "or auth.username like %:kw% " +
            "or a.content like %:kw% " +
            "or auth2.username like %:kw% " +
            "GROUP BY q.id ")
    Page<Question> findAll(Pageable pageable, @Param(value = "kw")String kw);

    @Transactional
    @Modifying
    @Query(
            value = "ALTER TABLE question AUTO_INCREMENT=1",
            nativeQuery = true
    )
    void truncate();

}
