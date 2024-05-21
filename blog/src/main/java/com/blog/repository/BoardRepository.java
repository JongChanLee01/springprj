package com.blog.repository;

import com.blog.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    void deleteByUserId(int id);
}
