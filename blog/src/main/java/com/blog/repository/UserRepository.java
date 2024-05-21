package com.blog.repository;

import com.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// JSP에서는 DAO에 해당한다.
// 자동으로 bean으로 등록이 된다.
@Repository // 생략가능
public interface UserRepository extends JpaRepository<User, Integer> {
    // 기본적인 CRUD 만 가능하다.
    // JPA Naming 쿼리
    // SELECT * FROM user WHERE username=? AND password=? ;

    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Query(value="SELECT * FROM user WHERE username=?1",nativeQuery=true)
    User findByUsername(@Param("username") String username);

    //@Query(value="SELECT * FROM user WHERE username=?1 AND password=?2",nativeQuery=true)
    // User login(String username, String password);
}
