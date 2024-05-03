package com.jpa4.service;

import com.jpa4.entity.Department;
import com.jpa4.entity.Student;
import com.jpa4.model.StudentEdit;
import com.jpa4.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    ModelMapper modelMapper = new ModelMapper();

    public StudentEdit findOne(int id) {
        Student studentEntity = studentRepository.findById(id).get();
        return toEditModel(studentEntity);
    }

    public Student findByStudentNo(String studentNo) {
        return studentRepository.findByStudentNo(studentNo);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    // public void insert(StudentEdit studentEdit) {
    //     // Student student = toEntity(studentEdit);
    //     Student student = toDto(studentEdit);
    //     studentRepository.save(student);
    // }
    public void insert(StudentEdit studentEdit,
                       BindingResult bindingResult) throws Exception {
        if (hasErrors(studentEdit, bindingResult))
            throw new Exception("입력 데이터 오류");
        Student student = toDto(studentEdit);
        studentRepository.save(student);
    }


    // public void update(StudentEdit studentEdit) {
    //     // Student student = toEntity(studentEdit);
    //     Student student = toDto(studentEdit);
    //     studentRepository.save(student);
    // }
    public void update(StudentEdit studentEdit,
                       BindingResult bindingResult) throws Exception {
        if (hasErrors(studentEdit, bindingResult))
            throw new Exception("입력 데이터 오류");
        Student student = toDto(studentEdit);
        studentRepository.save(student);
    }


    public void delete(int id) {
        studentRepository.deleteById(id);
    }


    // public Student toEntity(StudentEdit studentEdit) {
    //     Student studentEntity = new Student();
    //     studentEntity.setId(studentEdit.getId());
    //     studentEntity.setStudentNo(studentEdit.getStudentNo());
    //     studentEntity.setName(studentEdit.getName());
    //     Department department = new Department();
    //     department.setId(studentEdit.getDepartmentId());
    //     studentEntity.setDepartment(department);
    //     studentEntity.setEmail(studentEdit.getEmail());
    //     studentEntity.setPhone(studentEdit.getPhone());
    //     studentEntity.setSex(studentEdit.getSex());
    //     return studentEntity;
    // }
    public Student toDto(StudentEdit studentEdit) {
        return modelMapper.map(studentEdit, Student.class);
    }



    // public StudentEdit toEditModel(Student studentEntity) {
    //     StudentEdit studentEdit = new StudentEdit();
    //     studentEdit.setId(studentEntity.getId());
    //     studentEdit.setStudentNo(studentEntity.getStudentNo());
    //     studentEdit.setName(studentEntity.getName());
    //     studentEdit.setDepartmentId(studentEntity.getDepartment().getId());
    //     studentEdit.setEmail(studentEntity.getEmail());
    //     studentEdit.setPhone(studentEntity.getPhone());
    //     studentEdit.setSex(studentEntity.getSex());
    //     return studentEdit;
    // }
    public StudentEdit toEditModel(Student studentEntity) {
        return modelMapper.map(studentEntity, StudentEdit.class);
    }

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
