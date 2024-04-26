package com.validation1.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.validation1.dto.Department;
import com.validation1.mapper.DepartmentMapper;

@Service
public class DepartmentService {

    @Autowired
    public DepartmentMapper departmentMapper;

    public List<Department> findAll() {
        return departmentMapper.findAll();
    }

}
