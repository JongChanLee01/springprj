package com.mybatis4.service;

import java.util.List;

import com.mybatis4.model.Pagination;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import com.mybatis4.dto.Student;
import com.mybatis4.mapper.StudentMapper;
import com.mybatis4.model.StudentEdit;

@Service
public class StudentService {

    @Autowired StudentMapper studentMapper;
    ModelMapper modelMapper = new ModelMapper();

    public StudentEdit findOne(int id) {
        Student studentDto = studentMapper.findOne(id);
        return toEditModel(studentDto);
    }

    public Student findByStudentNo(String studentNo) {
        return studentMapper.findByStudentNo(studentNo);
    }

    // public List<Student> findAll() {
    //     return studentMapper.findAll();
    // }
    public List<Student> findAll(Pagination pagination) {
        // pagination.setRecordCount(studentMapper.getCount());
        pagination.setRecordCount(studentMapper.getCount(pagination));
        return studentMapper.findAll(pagination);
    }


    public void insert(StudentEdit studentEdit,
                       BindingResult bindingResult, Pagination pagination) throws Exception {
        if (hasErrors(studentEdit, bindingResult))
            throw new Exception("입력 데이터 오류");
        Student student = toDto(studentEdit);
        studentMapper.insert(student);

        // int lastPage = (int)Math.ceil((double)studentMapper.getCount() / pagination.getSz());
        int lastPage = (int)Math.ceil((double)studentMapper.getCount(pagination) / pagination.getSz());
        pagination.setPg(lastPage);
    }

    public void update(StudentEdit studentEdit,
                       BindingResult bindingResult) throws Exception {
        if (hasErrors(studentEdit, bindingResult))
            throw new Exception("입력 데이터 오류");
        Student student = toDto(studentEdit);
        studentMapper.update(student);
    }

    public void delete(int id) {
        studentMapper.delete(id);
    }
    public Student toDto(StudentEdit studentEdit) {
        return modelMapper.map(studentEdit, Student.class);
    }
    public StudentEdit toEditModel(Student studentDto) {
        return modelMapper.map(studentDto, StudentEdit.class);
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
