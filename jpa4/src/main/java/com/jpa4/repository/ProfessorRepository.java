package com.jpa4.repository;

import com.jpa4.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

}
