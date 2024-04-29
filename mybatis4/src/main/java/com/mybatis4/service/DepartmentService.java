package com.mybatis4.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mybatis4.dto.Department;
import com.mybatis4.mapper.DepartmentMapper;

@Service
public class DepartmentService {

    @Autowired
    public DepartmentMapper departmentMapper;

    public List<Department> findAll() {
        return departmentMapper.findAll();
    }

}
