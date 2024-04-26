package com.validation1.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.validation1.dto.Student;
import com.validation1.mapper.StudentMapper;
import com.validation1.model.StudentEdit;
import org.springframework.validation.BindingResult;

@Service
public class StudentService {

    @Autowired
    StudentMapper studentMapper;
    ModelMapper modelMapper = new ModelMapper();

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
        // Student studentDto = new Student();
        // studentDto.setId(studentEdit.getId());
        // studentDto.setStudentNo(studentEdit.getStudentNo());
        // studentDto.setName(studentEdit.getName());
        // studentDto.setDepartmentId(studentEdit.getDepartmentId());
        // studentDto.setEmail(studentEdit.getEmail());
        // studentDto.setPhone(studentEdit.getPhone());
        // studentDto.setSex(studentEdit.getSex());
        // return studentDto;
        return modelMapper.map(studentEdit, Student.class);
    }

    public StudentEdit toEditModel(Student studentDto) {
        // StudentEdit studentEdit = new StudentEdit();
        // studentEdit.setId(studentDto.getId());
        // studentEdit.setStudentNo(studentDto.getStudentNo());
        // studentEdit.setName(studentDto.getName());
        // studentEdit.setDepartmentId(studentDto.getDepartmentId());
        // studentEdit.setEmail(studentDto.getEmail());
        // studentEdit.setPhone(studentDto.getPhone());
        // studentEdit.setSex(studentDto.getSex());
        // return studentEdit;
        return modelMapper.map(studentDto, StudentEdit.class);
    }

    // 추가 - ModelMapper 예제
    public boolean hasErrors(StudentEdit studentEdit, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return true;
        Student student2 = findByStudentNo(studentEdit.getStudentNo());
        if (student2 != null && student2.getId() != studentEdit.getId()) {
            bindingResult.rejectValue("studentNo", null, "학번이 중복됩니다.");
            return true;
        }
        return false;
    }
}
