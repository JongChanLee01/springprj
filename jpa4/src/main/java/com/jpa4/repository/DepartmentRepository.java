package com.jpa4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jpa4.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>  {

}
