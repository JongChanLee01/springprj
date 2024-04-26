package com.validation1.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.validation1.dto.Student;
import com.validation1.mapper.StudentMapper;
import com.validation1.model.StudentEdit;

@Service
public class StudentService {

    @Autowired
    StudentMapper studentMapper;

    public StudentEdit findOne(int id) {
        Student studentDto = studentMapper.findOne(id);
        return toEditModel(studentDto);
    }

    public Student findByStudentNo(String studentNo) {
        return studentMapper.findByStudentNo(studentNo);
    }

    public List<Student> findAll() {
        // return studentMapper.findAll();
        
        // 위에처럼 바로 return 해도되고 아래처럼 풀어서 해도됨
        List<Student> students = studentMapper.findAll();
        return students;
    }

    public void insert(StudentEdit studentEdit) {
        Student student = toDto(studentEdit);
        studentMapper.insert(student);
    }

    public void update(StudentEdit studentEdit) {
        Student student = toDto(studentEdit);
        studentMapper.update(student);
    }

    public void delete(int id) {
        studentMapper.delete(id);
    }

    public Student toDto(StudentEdit studentEdit) {
        Student studentDto = new Student();
        studentDto.setId(studentEdit.getId());
        studentDto.setStudentNo(studentEdit.getStudentNo());
        studentDto.setName(studentEdit.getName());
        studentDto.setDepartmentId(studentEdit.getDepartmentId());
        studentDto.setEmail(studentEdit.getEmail());
        studentDto.setPhone(studentEdit.getPhone());
        studentDto.setSex(studentEdit.getSex());
        return studentDto;
    }

    public StudentEdit toEditModel(Student studentDto) {
        StudentEdit studentEdit = new StudentEdit();
        studentEdit.setId(studentDto.getId());
        studentEdit.setStudentNo(studentDto.getStudentNo());
        studentEdit.setName(studentDto.getName());
        studentEdit.setDepartmentId(studentDto.getDepartmentId());
        studentEdit.setEmail(studentDto.getEmail());
        studentEdit.setPhone(studentDto.getPhone());
        studentEdit.setSex(studentDto.getSex());
        return studentEdit;
    }
}
