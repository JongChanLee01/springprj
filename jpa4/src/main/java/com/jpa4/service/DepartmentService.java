package com.jpa4.service;

import com.jpa4.entity.Department;
import com.jpa4.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


// validation을 사용하면 컨트롤러에서 서비스를 거쳐서 실행되게 해주어야함
@Service
public class DepartmentService {
    @Autowired
    public DepartmentRepository departmentRepository;

    public List<Department> findAll(){
        return departmentRepository.findAll();
    }
}
