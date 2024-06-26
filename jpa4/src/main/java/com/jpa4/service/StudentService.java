package com.jpa4.service;

import com.jpa4.entity.Department;
import com.jpa4.entity.Student;
import com.jpa4.model.Pagination;
import com.jpa4.model.StudentEdit;
import com.jpa4.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class StudentService {
    @Data
    @AllArgsConstructor
    public static class Order {
        int value;
        String label;
    }

    static Order[] orders = new Order[] {
            new Order(0, "정렬 순서"),
            new Order(1, "학번 오름차순"),
            new Order(2, "학번 내림차순"),
            new Order(3, "이름 오름차순"),
            new Order(4, "학과 오름차순")
    };

    static Sort[] sorts = new Sort[] {
            Sort.by(Sort.Direction.ASC, "id"),
            Sort.by(Sort.Direction.ASC, "studentNo"),
            Sort.by(Sort.Direction.DESC, "studentNo"),
            Sort.by(Sort.Direction.ASC, "name"),
            Sort.by(Sort.Direction.ASC, "department.name"),
    };


    @Autowired
    StudentRepository studentRepository;

    ModelMapper modelMapper = new ModelMapper();

    public Order[] getOrders() {
        return orders;
    }

    public StudentEdit findOne(int id) {
        Student studentEntity = studentRepository.findById(id).get();
        return toEditModel(studentEntity);
    }

    public Student findByStudentNo(String studentNo) {
        return studentRepository.findByStudentNo(studentNo);
    }



    // public List<Student> findAll() {
    //     return studentRepository.findAll();
    // }

    // public List<Student> findAll(Pagination pagination) {
    //     PageRequest pageRequest = PageRequest.of(pagination.getPg() - 1,
    //             pagination.getSz(),
    //             Sort.Direction.ASC, "id");
    //     Page<Student> page = studentRepository.findAll(pageRequest);
    //     pagination.setRecordCount((int)page.getTotalElements());
    //     return page.getContent();
    // }

    public List<Student> findAll(Pagination pagination) {
        int orderIndex = pagination.getOd();

        // PageRequest pageRequest = PageRequest.of(pagination.getPg() - 1,
        //         pagination.getSz(), Sort.Direction.ASC, "id");
        PageRequest pageRequest = PageRequest.of(pagination.getPg() - 1,
                pagination.getSz(), sorts[orderIndex]);


        Page<Student> page;
        if (pagination.getSt().length() == 0)
            page = studentRepository.findAll(pageRequest);
        else
            page = studentRepository.findByStudentNoOrNameStartsWithOrDepartmentNameStartsWith(
                    pagination.getSt(), pagination.getSt(), pagination.getSt(), pageRequest);
        pagination.setRecordCount((int)page.getTotalElements());
        return page.getContent();
    }




    // public void insert(StudentEdit studentEdit) {
    //     // Student student = toEntity(studentEdit);
    //     Student student = toDto(studentEdit);
    //     studentRepository.save(student);
    // }

    // public void insert(StudentEdit studentEdit,
    //                    BindingResult bindingResult) throws Exception {
    //     if (hasErrors(studentEdit, bindingResult))
    //         throw new Exception("입력 데이터 오류");
    //     Student student = toDto(studentEdit);
    //     studentRepository.save(student);
    // }

    public void insert(StudentEdit studentEdit, BindingResult bindingResult,
                       Pagination pagination) throws Exception {
        if (hasErrors(studentEdit, bindingResult))
            throw new Exception("입력 데이터 오류");
        Student student = toDto(studentEdit);
        studentRepository.save(student);

        pagination.setSt("");
        pagination.setOd(0);

        int lastPage = (int)Math.ceil((double)studentRepository.count() / pagination.getSz());
        pagination.setPg(lastPage);
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
