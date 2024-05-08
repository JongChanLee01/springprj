package com.bbs1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bbs1.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>  {

    User findByLoginName(String loginName);

    Page<User> findByLoginNameStartsWith(String loginName, Pageable pageable);
    Page<User> findByNameStartsWith(String loginName, Pageable pageable);
    Page<User> findByUserType(String userType, Pageable pageable);

}
