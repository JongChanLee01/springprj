package com.sbb.repository;

import com.sbb.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    
    Question findBySubject(String subject);

    Question findBySubjectAndContent(String subject, String content);

    List<Question> findBySubjectLike(String subject);

    Page<Question> findAll(Pageable pageable);


    @Query("select q "
            + "from Question q "
            + "join SiteUser u on q.author=u "
            + "where u.username = :username "
            + "order by q.createDate desc ")
    List<Question> findCurrentQuestion(@Param("username") String username,
                                       Pageable pageable);
}
