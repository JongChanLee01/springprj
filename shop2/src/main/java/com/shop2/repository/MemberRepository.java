package com.shop2.repository;

import com.shop2.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);


    @Query(value = "Select * "
            + "from member where email=:email"
    , nativeQuery = true)
    Optional<Member> findByEmail2(@Param("email") String email);
}