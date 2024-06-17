package com.shop2.repository;

import com.shop2.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    // Page<Question> findAll(Pageable pageable);

    @Query("select "
            + "distinct q "
            + "from Question q "
            + "left outer join Member m1 on q.author=m1 "
            + "left outer join Answer a on a.question=q "
            + "left outer join Member m2 on a.author=m2 "
            + "where "
            + "   q.subject like %:kw% "
            + "   or q.content like %:kw% "
            + "   or m1.email like %:kw% "
            + "   or a.content like %:kw% "
            + "   or m2.email like %:kw% ")
    Page<Question> findAllByKeyword(@Param("kw") String kw, Pageable pageable);


}
