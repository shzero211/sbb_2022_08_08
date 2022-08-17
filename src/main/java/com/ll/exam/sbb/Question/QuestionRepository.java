package com.ll.exam.sbb.Question;

import com.ll.exam.sbb.base.RepositoryUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Integer>, RepositoryUtil {
    Question findBySubject(String subject);
    Question findBySubjectAndContent(String subject,String content);
    List<Question> findBySubjectLike(String subject);

    @Override
    Page<Question> findAll(Pageable pageable);

    @Transactional
    @Modifying
    @Query(
            value = "ALTER TABLE question AUTO_INCREMENT=1",
            nativeQuery = true
    )
    void truncate();

}
