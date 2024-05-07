package com.bbs1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbs1.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>  {

    User findByLoginName(String loginName);

}
