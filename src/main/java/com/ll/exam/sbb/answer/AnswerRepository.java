package com.ll.exam.sbb.answer;

import com.ll.exam.sbb.base.RepositoryUtil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer,Integer>, RepositoryUtil {
@Transactional
@Modifying
@Query(value = "ALTER TABLE answer AUTO_INCREMENT=1",nativeQuery = true)
    public void truncate();

}
