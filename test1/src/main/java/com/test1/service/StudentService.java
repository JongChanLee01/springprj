package com.test1.service;

import com.test1.dto.Student;
import com.test1.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    public StudentMapper studentMapper;

    public List<Student> findAll() {
        return studentMapper.findAll();
    }

    public Student findById(int id) {
        return studentMapper.findById(id);
    }

    public boolean hasErrors(Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return true;
        Student s = studentMapper.findByStudentNo(student.getStudentNo());
        if (s != null) {
            bindingResult.rejectValue("studentNo", null, "학번이 중복됩니다.");
            return true;
        }
        return false;
    }

    public void insert(Student student) {
        if (studentMapper.findByStudentNo(student.getStudentNo()) == null)
            studentMapper.insert(student);
    }

    public void deleteById(int id) {
        studentMapper.deleteById(id);
    }

}
