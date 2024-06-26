package com.sbb.repository;

import com.sbb.entity.Answer;
import com.sbb.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    @Query("select a "
            + "from Answer a "
            + "join SiteUser u on a.author=u "
            + "where u.username = :username "
            + "order by a.createDate desc ")
    List<Answer> findCurrentAnswer(@Param("username") String username, Pageable pageable);


    Page<Answer> findAllByQuestion(Question question, Pageable pageable);
}